package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.eduardotanaka.knowdown.model.Question;
import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Exercicio5Activity extends AppCompatActivity {
    @BindView(R.id.txtPontos5)
    TextView txtPontos5;

    @BindView(R.id.edtConteudo5)
    TextView edtConteudo;

    @BindView(R.id.btnEx5Opcao1)
    TextView txtOpcao1;

    @BindView(R.id.btnEx5Opcao2)
    TextView txtOpcao2;

    @BindView(R.id.btnEx5Opcao3)
    TextView txtOpcao3;

    @BindView(R.id.btnEx5Opcao4)
    TextView txtOpcao4;

    @BindView(R.id.btnVerificar5)
    Button btnVerificar5;

    @BindView(R.id.imgEx5Avatar)
    ImageView imgEx5Avatar;

    private String respostaCerta;
    private String respostaUsuario;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio5);

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontos5.setText(String.valueOf(userDB.getScore()));

                    Random r = new Random();
                    int n = r.nextInt(3) + 1000;
                    mDatabase.child("questions").child(String.valueOf(n)).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Question question = dataSnapshot.getValue(Question.class);

                                txtOpcao1.setText(question.getAnswers().get(1).getDescription().toUpperCase());
                                txtOpcao2.setText(question.getAnswers().get(2).getDescription().toUpperCase());
                                txtOpcao3.setText(question.getAnswers().get(3).getDescription().toUpperCase());
                                txtOpcao4.setText(question.getAnswers().get(4).getDescription().toUpperCase());
                                respostaCerta = question.getCorrect().toUpperCase();

                                btnVerificar5.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        }
                    );
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private boolean removerPalavra(String s) {
        List<String> lista = Arrays.asList(edtConteudo.getText().toString().split(" "));
        if (lista.contains(s) == false) {
            edtConteudo.setText(edtConteudo.getText().toString() + s + " ");
            return true;
        } else {
            StringBuilder builder = new StringBuilder();
            for(String string : lista) {
                if(!string.equals(s)) {
                    builder.append(string + " ");
                }
            }
            edtConteudo.setText(builder.toString());
            return false;
        }
    }

    public void mudarFundoBotao(TextView txtOpcao){
        if(removerPalavra(txtOpcao.getText().toString())) {
            txtOpcao.setBackgroundResource(R.drawable.shape_pergunta_pressed);
        } else {
            txtOpcao.setBackgroundResource(R.drawable.shape_pergunta);
        }
    }

    @OnClick(R.id.btnEx5Opcao1)
    public void opcao1(View view) {
        mudarFundoBotao(txtOpcao1);
    }

    @OnClick(R.id.btnEx5Opcao2)
    public void opcao2(View view) {
        mudarFundoBotao(txtOpcao2);
    }

    @OnClick(R.id.btnEx5Opcao3)
    public void opcao3(View view) {
        mudarFundoBotao(txtOpcao3);
    }

    @OnClick(R.id.btnEx5Opcao4)
    public void opcao4(View view) {
        mudarFundoBotao(txtOpcao4);
    }

    @OnClick(R.id.imgEx5Avatar)
    public void avatar(View view) {
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnVerificar5)
    public void verificar() {

        respostaUsuario = (edtConteudo.getText().toString().toUpperCase().trim());
        if(respostaUsuario.equals(respostaCerta)) {
            userDB.setScore(userDB.getScore() + 10);
            userDB.setCoin(userDB.getCoin() + 10);
            userDB.setAcerto(userDB.getAcerto() + 1);
            userDB.updateDB();

            Intent intent = new Intent(this, AcertoActivity.class);
            intent.putExtra("feedback", respostaCerta);
            startActivity(intent);
            finish();
        } else {
            userDB.setErro(userDB.getErro() + 1);
            userDB.updateDB();

            Intent intent = new Intent(this, ErroActivity.class);
            intent.putExtra("feedback", respostaCerta);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
