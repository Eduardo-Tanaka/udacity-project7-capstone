package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.eduardotanaka.knowdown.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends CommonActivity {

    @BindView(R.id.edtLoginUser)
    EditText usuario;

    @BindView(R.id.edtLoginPassword)
    EditText password;

    @BindView(R.id.btnNovaSenha)
    Button btnNovaSenha;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private User user;

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        ButterKnife.bind(this);

        initUser();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = getFirebaseAuthResultHandler();
    }

    private FirebaseAuth.AuthStateListener getFirebaseAuthResultHandler(){
        FirebaseAuth.AuthStateListener callback = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser userFirebase = firebaseAuth.getCurrentUser();
                if( userFirebase != null && user.getId() == null ){
                    user.saveIdSP( LoginActivity.this, userFirebase.getUid() );

                    Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                    startActivity(intent);

                    finish();
                }
            }
        };
        return( callback );
    }

    protected void initUser(){
        user = new User();
        user.setEmail( usuario.getText().toString().trim() );
        user.setPassword( password.getText().toString() );
    }

    @OnClick(R.id.btnLogin)
    public void login(final View view) {
        if(!validaCampos()){
            return;
        }

        openProgressBar();
        initUser();
        verifyLogin();
    }

    private void verifyLogin(){
        user.saveTokenSP( LoginActivity.this, "" );
        mAuth.signInWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( !task.isSuccessful() ){
                    closeProgressBar();
                    showSnackbar(getString(R.string.login_error));
                }
            }
        });
    }

    private boolean validaCampos() {
        boolean retorno = true;
        if(usuario.getText().toString().equals("")){
            usuario.setError(getResources().getString(R.string.msg_campo_obrigatorio));
            retorno = false;
        }
        else if(password.getText().toString().equals("")){
            password.setError(getResources().getString(R.string.msg_campo_obrigatorio));
            retorno = false;
        }
        return retorno;
    }

    @OnClick(R.id.btnRegistre_se)
    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.imgLoginRanking)
    public void ranking1(View view) {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnRanking)
    public void ranking2(View view) {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnNovaSenha)
    public void senha(View v) {
        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.forgot_password, null);

        final EditText edtEsqueciSenha = (EditText) view.findViewById(R.id.edtEsqueciSenha);

        view.findViewById(R.id.btnEsqueciSenhaCancelar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnEsqueciSenhaEnviar).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(edtEsqueciSenha.getText().toString().equals("")){
                    edtEsqueciSenha.setError(getResources().getString(R.string.msg_campo_obrigatorio));
                } else {
                    mAuth.sendPasswordResetEmail(edtEsqueciSenha.getText().toString());
                    showToast(getString(R.string.email_sent));
                    alerta.dismiss();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.forgot_password));
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener( mAuthListener );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mAuthListener != null ){
            mAuth.removeAuthStateListener( mAuthListener );
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
