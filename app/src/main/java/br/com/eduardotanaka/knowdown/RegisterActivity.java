package br.com.eduardotanaka.knowdown;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.com.eduardotanaka.knowdown.model.GuardaRoupa;
import br.com.eduardotanaka.knowdown.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends CommonActivity implements DatabaseReference.CompletionListener {

    @BindView(R.id.edtRegisterUserName)
    EditText userName;

    @BindView(R.id.edtRegisterEmail)
    EditText email;

    @BindView(R.id.edtPassword)
    EditText password;

    @BindView(R.id.edtPassword2)
    EditText password2;

    @BindView(R.id.imgGirl)
    ImageView imgGirl;

    @BindView(R.id.imgBoy)
    ImageView imgBoy;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    private String gender;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    user.setId( firebaseUser.getUid() );
                    user.saveDB( RegisterActivity.this );
                } else {
                    // User is signed out
                    Log.d("SIGNUP", "onAuthStateChanged:signed_out");
                    closeProgressBar();
                }
            }
        };
    }

    @OnClick(R.id.imgGirl)
    public void girl(View view) {
        imgBoy.setAlpha(0.3f);
        imgGirl.setAlpha(1f);
        gender = "0";
    }

    @OnClick(R.id.imgBoy)
    public void boy(View view) {
        imgGirl.setAlpha(0.3f);
        imgBoy.setAlpha(1f);
        gender = "1";
    }

    @OnClick(R.id.btnRegister)
    public void register(View view) {
        btnRegister.setEnabled(false);
        if(!validaCampos()){
            btnRegister.setEnabled(false);
            return;
        }

        openProgressBar();
        initUser();
        saveUser();
    }

    private void saveUser(){
        mAuth.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    closeProgressBar();
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showSnackbar( e.getMessage() );
            }
        });
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        mAuth.signOut();

        showToast(getString(R.string.success_register));
        closeProgressBar();
        finish();
    }

    private boolean validaCampos() {
        boolean retorno = true;
        String regExEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        // Validação dos inputs
        if(userName.getText().toString().equals("")){
            userName.setError(getResources().getString(R.string.msg_campo_obrigatorio));
            retorno = false;
        }
        if(email.getText().toString().equals("")){
            email.setError(getResources().getString(R.string.msg_campo_obrigatorio));
            retorno = false;
        }
        if(password.getText().toString().equals("")){
            password.setError(getResources().getString(R.string.msg_senha_vazia));
            retorno = false;
        } else if(!password.getText().toString().equals(password2.getText().toString())){
            password2.setError(getResources().getString(R.string.msg_senha_diferente));
            retorno = false;
        } else if(password.getText().toString().length() < 6){
            password.setError(getResources().getString(R.string.msg_senha_minimo));
            retorno = false;
        }
        if(!email.getText().toString().matches(regExEmail)){
            email.setError(getResources().getString(R.string.msg_email_invalido));
            retorno = false;
        }
        if(gender == null) {
            showSnackbar(getResources().getString(R.string.choose_avatar));
            retorno = false;
        }

        return retorno;
    }

    protected void initUser(){
        user = new User();
        user.setName( userName.getText().toString() );
        user.setEmail( email.getText().toString() );
        user.setPassword( password.getText().toString() );
        user.setGender(gender);
        user.setScore(0);
        user.setCoin(0);
        user.setAcerto(0);
        user.setErro(0);
        List<GuardaRoupa> gr = new ArrayList<>();
        user.setGuardaroupa(gr);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
