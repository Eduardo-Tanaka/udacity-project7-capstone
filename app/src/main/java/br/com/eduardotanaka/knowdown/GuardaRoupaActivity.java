package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuardaRoupaActivity extends AppCompatActivity {

    @BindView(R.id.imgGuardaRoupaCamisaOpcao1)
    ImageView imgGuardaRoupaCamisaOpcao1;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao2)
    ImageView imgGuardaRoupaCamisaOpcao2;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao3)
    ImageView imgGuardaRoupaCamisaOpcao3;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao4)
    ImageView imgGuardaRoupaCamisaOpcao4;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao5)
    ImageView imgGuardaRoupaCamisaOpcao5;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao6)
    ImageView imgGuardaRoupaCamisaOpcao6;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao7)
    ImageView imgGuardaRoupaCamisaOpcao7;

    @BindView(R.id.imgGuardaRoupaCamisaOpcao8)
    ImageView imgGuardaRoupaCamisaOpcao8;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao1)
    ImageView imgGuardaRoupaCalcaOpcao1;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao2)
    ImageView imgGuardaRoupaCalcaOpcao2;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao3)
    ImageView imgGuardaRoupaCalcaOpcao3;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao4)
    ImageView imgGuardaRoupaCalcaOpcao4;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao5)
    ImageView imgGuardaRoupaCalcaOpcao5;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao6)
    ImageView imgGuardaRoupaCalcaOpcao6;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao7)
    ImageView imgGuardaRoupaCalcaOpcao7;

    @BindView(R.id.imgGuardaRoupaCalcaOpcao8)
    ImageView imgGuardaRoupaCalcaOpcao8;

    @BindView(R.id.imgGuardaRoupaSapatoOpcao1)
    ImageView imgGuardaRoupaSapatoOpcao1;

    @BindView(R.id.imgGuardaRoupaSapatoOpcao2)
    ImageView imgGuardaRoupaSapatoOpcao2;

    @BindView(R.id.imgGuardaRoupaSapatoOpcao3)
    ImageView imgGuardaRoupaSapatoOpcao3;

    @BindView(R.id.imgGuardaRoupaSapatoOpcao4)
    ImageView imgGuardaRoupaSapatoOpcao4;

    @BindView(R.id.txtPontosGuardaRoupa)
    TextView txtPontosGuardaRoupa;

    @BindView(R.id.txtGuardaRoupaAvatar)
    TextView txtGuardaRoupaAvatar;

    @BindView(R.id.imgGuardaRoupaAvatar)
    ImageView imgGuardaRoupaAvatar;

    private AlertDialog alerta;
    private DatabaseReference mDatabase;
    private User userDB;
    private int[] camisa;
    private int[] calca;
    private int[] sapato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarda_roupa);

        camisa = new int[8];
        calca = new int[8];
        sapato = new int[4];

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontosGuardaRoupa.setText(String.valueOf(userDB.getScore()));

                    int j = 1;
                    int k = 1;
                    int x = 1;
                    if(userDB.getGuardaroupa() != null){
                        for(int i = 0; i < userDB.getGuardaroupa().size(); i++) {
                            int img = selecionarRoupa(userDB.getGuardaroupa().get(i).getRoupa());
                            if(userDB.getGuardaroupa().get(i).getId() == 1) {
                                if(j == 1) {
                                    camisa[0] = img;
                                    imgGuardaRoupaCamisaOpcao1.setImageResource(img);
                                } else if(j == 2) {
                                    camisa[1] = img;
                                    imgGuardaRoupaCamisaOpcao2.setImageResource(img);
                                } else if(j == 3) {
                                    camisa[2] = img;
                                    imgGuardaRoupaCamisaOpcao3.setImageResource(img);
                                } else if(j == 4) {
                                    camisa[3] = img;
                                    imgGuardaRoupaCamisaOpcao4.setImageResource(img);
                                } else if(j == 5) {
                                    camisa[4] = img;
                                    imgGuardaRoupaCamisaOpcao5.setImageResource(img);
                                } else if(j == 6) {
                                    camisa[5] = img;
                                    imgGuardaRoupaCamisaOpcao6.setImageResource(img);
                                } else if(j == 7) {
                                    camisa[6] = img;
                                    imgGuardaRoupaCamisaOpcao7.setImageResource(img);
                                } else if(j == 8) {
                                    camisa[7] = img;
                                    imgGuardaRoupaCamisaOpcao8.setImageResource(img);
                                }
                                j++;
                            } else if(userDB.getGuardaroupa().get(i).getId() == 2) {
                                if(k == 1) {
                                    calca[0] = img;
                                    imgGuardaRoupaCalcaOpcao1.setImageResource(img);
                                } else if(k == 2) {
                                    calca[1] = img;
                                    imgGuardaRoupaCalcaOpcao2.setImageResource(img);
                                } else if(k == 3) {
                                    calca[2] = img;
                                    imgGuardaRoupaCalcaOpcao3.setImageResource(img);
                                } else if(k == 4) {
                                    calca[3] = img;
                                    imgGuardaRoupaCalcaOpcao4.setImageResource(img);
                                } else if(k == 5) {
                                    calca[4] = img;
                                    imgGuardaRoupaCalcaOpcao5.setImageResource(img);
                                } else if(k == 6) {
                                    calca[5] = img;
                                    imgGuardaRoupaCalcaOpcao6.setImageResource(img);
                                } else if(k == 7) {
                                    calca[6] = img;
                                    imgGuardaRoupaCalcaOpcao7.setImageResource(img);
                                } else if(k == 8) {
                                    calca[7] = img;
                                    imgGuardaRoupaCalcaOpcao8.setImageResource(img);
                                }
                                k++;
                            } else if(userDB.getGuardaroupa().get(i).getId() == 3) {
                                if(x == 1) {
                                    sapato[0] = img;
                                    imgGuardaRoupaSapatoOpcao1.setImageResource(img);
                                } else if(x == 2) {
                                    sapato[1] = img;
                                    imgGuardaRoupaSapatoOpcao2.setImageResource(img);
                                } else if(x == 3) {
                                    sapato[2] = img;
                                    imgGuardaRoupaSapatoOpcao3.setImageResource(img);
                                } else if(x == 4) {
                                    sapato[3] = img;
                                    imgGuardaRoupaSapatoOpcao4.setImageResource(img);
                                }
                                x++;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }

    private int selecionarRoupa(int roupa) {
        int roupaId = 0;
        if(userDB.getGender() != "0"){
            if(roupa == 1) {
                roupaId = R.drawable.camisa_curta_preta;
            } else if(roupa == 2) {
                roupaId = R.drawable.camisa_curta_rosa;
            } else if(roupa == 3) {
                roupaId = R.drawable.camisa_curta_verde;
            } else if(roupa == 4) {
                roupaId = R.drawable.camisa_curta_amarela;
            } else if(roupa == 5) {
                roupaId = R.drawable.camisa_curta_azul;
            } else if(roupa == 6) {
                roupaId = R.drawable.camisa_curta_vermelha;
            } else if(roupa == 7) {
                roupaId = R.drawable.calca_azul;
            } else if(roupa == 8) {
                roupaId = R.drawable.calca_cinza;
            } else if(roupa == 9) {
                roupaId = R.drawable.calca_vermelha;
            } else if(roupa == 10) {
                roupaId = R.drawable.calca_preta;
            } else if(roupa == 11) {
                roupaId = R.drawable.calca_verde;
            } else if(roupa == 12) {
                roupaId = R.drawable.calca_vinho;
            } else if(roupa == 13) {
                roupaId = R.drawable.sapato_azul;
            } else if(roupa == 14) {
                roupaId = R.drawable.sapato_cinza;
            } else if(roupa == 15) {
                roupaId = R.drawable.sapato_rosa;
            } else if(roupa == 16) {
                roupaId = R.drawable.sapato_azul;
            } else if(roupa == 17) {
                roupaId = R.drawable.sapato_cinza;
            } else if(roupa == 18) {
                roupaId = R.drawable.sapato_rosa;
            }
        } else {
            if(roupa == 1) {
                roupaId = R.drawable.camisa_curta_preta;
            } else if(roupa == 2) {
                roupaId = R.drawable.camisa_curta_rosa;
            } else if(roupa == 3) {
                roupaId = R.drawable.camisa_curta_verde;
            } else if(roupa == 4) {
                roupaId = R.drawable.camisa_curta_amarela;
            } else if(roupa == 5) {
                roupaId = R.drawable.camisa_curta_azul;
            } else if(roupa == 6) {
                roupaId = R.drawable.camisa_curta_vermelha;
            } else if(roupa == 7) {
                roupaId = R.drawable.calca_azul;
            } else if(roupa == 8) {
                roupaId = R.drawable.calca_cinza;
            } else if(roupa == 9) {
                roupaId = R.drawable.calca_vermelha;
            } else if(roupa == 10) {
                roupaId = R.drawable.calca_preta;
            } else if(roupa == 11) {
                roupaId = R.drawable.calca_verde;
            } else if(roupa == 12) {
                roupaId = R.drawable.calca_vinho;
            } else if(roupa == 13) {
                roupaId = R.drawable.sapato_azul;
            } else if(roupa == 14) {
                roupaId = R.drawable.sapato_cinza;
            } else if(roupa == 15) {
                roupaId = R.drawable.sapato_rosa;
            } else if(roupa == 16) {
                roupaId = R.drawable.sapato_azul;
            } else if(roupa == 17) {
                roupaId = R.drawable.sapato_cinza;
            } else if(roupa == 18) {
                roupaId = R.drawable.sapato_rosa;
            }
        }
        return roupaId;
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao1)
    public void camisa1(View v){
        int id = camisa[0];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao2)
    public void camisa2(View v){
        int id = camisa[1];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao3)
    public void camisa3(View v){
        int id = camisa[2];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao4)
    public void camisa4(View v){
        int id = camisa[3];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao5)
    public void camisa5(View v){
        int id = camisa[4];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao6)
    public void camisa6(View v){
        int id = camisa[5];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao7)
    public void camisa7(View v){
        int id = camisa[6];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCamisaOpcao8)
    public void camisa8(View v){
        int id = camisa[7];
        alert(id, 1);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao1)
    public void calca1(View v){
        int id = calca[0];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao2)
    public void calca2(View v){
        int id = calca[1];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao3)
    public void calca3(View v){
        int id = calca[2];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao4)
    public void calca4(View v){
        int id = calca[3];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao5)
    public void calca5(View v){
        int id = calca[4];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao6)
    public void calca6(View v){
        int id = calca[5];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao7)
    public void calca7(View v){
        int id = calca[6];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaCalcaOpcao8)
    public void calca8(View v){
        int id = calca[7];
        alert(id, 2);
    }

    @OnClick(R.id.imgGuardaRoupaSapatoOpcao1)
    public void sapato1(View v){
        int id = sapato[0];
        alert(id, 3);
    }

    @OnClick(R.id.imgGuardaRoupaSapatoOpcao2)
    public void sapato2(View v){
        int id = sapato[1];
        alert(id, 3);
    }

    @OnClick(R.id.imgGuardaRoupaSapatoOpcao3)
    public void sapato3(View v){
        int id = sapato[2];
        alert(id, 3);
    }

    @OnClick(R.id.imgGuardaRoupaSapatoOpcao4)
    public void sapato4(View v){
        int id = sapato[3];
        alert(id, 3);
    }

    @OnClick(R.id.txtGuardaRoupaAvatar)
    public void avatar(View v){
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imgGuardaRoupaAvatar)
    public void avatar2(View v){
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
        finish();
    }

    private void alert(final int id, final int tipo){
        LayoutInflater li = getLayoutInflater();
        View vi = li.inflate(R.layout.alerta, null);
        vi.findViewById(R.id.imgAlerta).setBackgroundResource(id);
        vi.findViewById(R.id.btnCancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });
        vi.findViewById(R.id.btnConfirmar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tipo == 1) {
                    userDB.setCamisa(id);
                } else if(tipo == 2) {
                    userDB.setCalca(id);
                } else if(tipo == 3) {
                    userDB.setSapato(id);
                }
                userDB.updateDB();
                alerta.dismiss();

                Intent intent = new Intent(GuardaRoupaActivity.this, AvatarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(GuardaRoupaActivity.this);
        builder.setCancelable(true);
        builder.setView(vi);
        alerta = builder.create();
        alerta.show();
    }
}
