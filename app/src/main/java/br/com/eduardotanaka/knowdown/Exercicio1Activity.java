package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import br.com.eduardotanaka.knowdown.model.Question;
import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import br.com.eduardotanaka.knowdown.widget.Sync;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Exercicio1Activity extends AppCompatActivity {

    @BindView(R.id.txtExercicio1)
    TextView txtExercicio1;

    @BindView(R.id.txtPontos1)
    TextView txtPontos1;

    @BindView(R.id.btnEx1Opcao1)
    Button btnEx1Opcao1;

    @BindView(R.id.btnEx1Opcao2)
    Button btnEx1Opcao2;

    @BindView(R.id.btnEx1Opcao3)
    Button btnEx1Opcao3;

    @BindView(R.id.btnEx1Opcao4)
    Button btnEx1Opcao4;

    @BindView(R.id.btnEx1Opcao5)
    Button btnEx1Opcao5;

    @BindView(R.id.btnVerificar1)
    Button btnVerificar1;

    private boolean op1;
    private boolean op2;
    private boolean op3;
    private boolean op4;
    private boolean op5;

    private boolean op1Correta;
    private boolean op2Correta;
    private boolean op3Correta;
    private boolean op4Correta;
    private boolean op5Correta;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio1);

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontos1.setText(userDB.getScore()+"");
                    mDatabase.child("questions").child("1").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Question question = dataSnapshot.getValue(Question.class);
                                txtExercicio1.setText(question.getDescricao());

                                ArrayList<Integer> list = new ArrayList<Integer>();
                                ArrayList<Integer> listAnswers = new ArrayList<Integer>();
                                for (int i = 1; i <= 11; i++) {
                                    list.add(new Integer(i));
                                }
                                Collections.shuffle(list);
                                for (int i = 0; i < 5; i++) {
                                    listAnswers.add(list.get(i));
                                }

                                descreverAlternativaBotao(btnEx1Opcao1, question.getAnswers().get(listAnswers.get(0)).getDescription().toUpperCase());
                                descreverAlternativaBotao(btnEx1Opcao2, question.getAnswers().get(listAnswers.get(1)).getDescription().toUpperCase());
                                descreverAlternativaBotao(btnEx1Opcao3, question.getAnswers().get(listAnswers.get(2)).getDescription().toUpperCase());
                                descreverAlternativaBotao(btnEx1Opcao4, question.getAnswers().get(listAnswers.get(3)).getDescription().toUpperCase());
                                descreverAlternativaBotao(btnEx1Opcao5, question.getAnswers().get(listAnswers.get(4)).getDescription().toUpperCase());

                                op1Correta = question.getAnswers().get(listAnswers.get(0)).isCorrect();
                                op2Correta = question.getAnswers().get(listAnswers.get(1)).isCorrect();
                                op3Correta = question.getAnswers().get(listAnswers.get(2)).isCorrect();
                                op4Correta = question.getAnswers().get(listAnswers.get(3)).isCorrect();
                                op5Correta = question.getAnswers().get(listAnswers.get(4)).isCorrect();

                                op1 = false;
                                op2 = false;
                                op3 = false;
                                op4 = false;
                                op5 = false;

                                btnVerificar1.setVisibility(View.VISIBLE);
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

    public void descreverAlternativaBotao(Button btn, String descricao){
        btn.setText(descricao.toUpperCase());
    }

    @OnClick(R.id.btnEx1Opcao1)
    public void opcao1(View view) {
        if(op1) {
            op1 = false;
            btnEx1Opcao1.setBackgroundResource(R.drawable.shape_pergunta);
        } else {
            op1 = true;
            btnEx1Opcao1.setBackgroundResource(R.drawable.shape_pergunta_pressed);
        }
    }

    @OnClick(R.id.btnEx1Opcao2)
    public void opcao2(View view) {
        if(op2) {
            op2 = false;
            btnEx1Opcao2.setBackgroundResource(R.drawable.shape_pergunta);
        } else {
            op2 = true;
            btnEx1Opcao2.setBackgroundResource(R.drawable.shape_pergunta_pressed);
        }
    }

    @OnClick(R.id.btnEx1Opcao3)
    public void opcao3(View view) {
        if(op3) {
            op3 = false;
            btnEx1Opcao3.setBackgroundResource(R.drawable.shape_pergunta);
        } else {
            op3 = true;
            btnEx1Opcao3.setBackgroundResource(R.drawable.shape_pergunta_pressed);
        }
    }

    @OnClick(R.id.btnEx1Opcao4)
    public void opcao4(View view) {
        if(op4) {
            op4 = false;
            btnEx1Opcao4.setBackgroundResource(R.drawable.shape_pergunta);
        } else {
            op4 = true;
            btnEx1Opcao4.setBackgroundResource(R.drawable.shape_pergunta_pressed);
        }
    }

    @OnClick(R.id.btnEx1Opcao5)
    public void opcao5(View view) {
        if(op5) {
            op5 = false;
            btnEx1Opcao5.setBackgroundResource(R.drawable.shape_pergunta);
        } else {
            op5 = true;
            btnEx1Opcao5.setBackgroundResource(R.drawable.shape_pergunta_pressed);
        }
    }

    @OnClick(R.id.imgEx1Avatar)
    public void avatar(View view) {
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnVerificar1)
    public void verificar(View view) {
        String respostaCerta = "";
        if(op1Correta) {
            respostaCerta += btnEx1Opcao1.getText().toString() + "\n";
        }
        if(op2Correta) {
            respostaCerta += btnEx1Opcao2.getText().toString() + "\n";
        }
        if(op3Correta) {
            respostaCerta += btnEx1Opcao3.getText().toString() + "\n";
        }
        if(op4Correta) {
            respostaCerta += btnEx1Opcao4.getText().toString() + "\n";
        }
        if(op5Correta) {
            respostaCerta += btnEx1Opcao5.getText().toString() + "\n";
        }

        if(op1 == op1Correta && op2 == op2Correta && op3 == op3Correta && op4 == op4Correta && op5 == op5Correta) {
            // update user
            userDB.setScore(userDB.getScore() + 10);
            userDB.setCoin(userDB.getCoin() + 10);
            userDB.setAcerto(userDB.getAcerto() + 1);
            userDB.updateDB();

            Sync.start(this);

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
    public void onResume(){
        super.onResume();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
