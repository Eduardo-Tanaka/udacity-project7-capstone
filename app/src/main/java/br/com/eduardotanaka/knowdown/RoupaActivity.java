package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RoupaActivity extends AppCompatActivity {

    @BindView(R.id.imgRoupaOpcao1)
    ImageView imgRoupaOpcao1;

    @BindView(R.id.imgRoupaOpcao2)
    ImageView imgRoupaOpcao2;

    @BindView(R.id.imgRoupaOpcao3)
    ImageView imgRoupaOpcao3;

    @BindView(R.id.imgRoupaOpcao4)
    ImageView imgRoupaOpcao4;

    @BindView(R.id.imgRoupaOpcao5)
    ImageView imgRoupaOpcao5;

    @BindView(R.id.imgRoupaOpcao6)
    ImageView imgRoupaOpcao6;

    @BindView(R.id.txtRoupaOpcao1)
    TextView txtRoupaOpcao1;

    @BindView(R.id.txtRoupaOpcao2)
    TextView txtRoupaOpcao2;

    @BindView(R.id.txtRoupaOpcao3)
    TextView txtRoupaOpcao3;

    @BindView(R.id.txtRoupaOpcao4)
    TextView txtRoupaOpcao4;

    @BindView(R.id.txtRoupaOpcao5)
    TextView txtRoupaOpcao5;

    @BindView(R.id.txtRoupaOpcao6)
    TextView txtRoupaOpcao6;

    @BindView(R.id.txtPontosRoupa)
    TextView txtPontos;

    private int tipo;
    private int roupaId1;
    private int roupaId2;
    private int roupaId3;
    private int roupaId4;
    private int roupaId5;
    private int roupaId6;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roupa);

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontos.setText(userDB.getCoin()+"");

                    carregarImagens(1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }

    @OnClick(R.id.imgRoupaOpcao1)
    public void opcao1(View view) {
        Intent intent = new Intent(this, CompraActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("roupaId", roupaId1);
        intent.putExtra("preco", Integer.parseInt(txtRoupaOpcao1.getText().toString().split(" ")[1]));
        startActivity(intent);
    }

    @OnClick(R.id.imgRoupaOpcao2)
    public void opcao2(View view) {
        Intent intent = new Intent(this, CompraActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("roupaId", roupaId2);
        intent.putExtra("preco", Integer.parseInt(txtRoupaOpcao2.getText().toString().split(" ")[1]));
        startActivity(intent);
    }

    @OnClick(R.id.imgRoupaOpcao3)
    public void opcao3(View view) {
        Intent intent = new Intent(this, CompraActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("roupaId", roupaId3);
        intent.putExtra("preco", Integer.parseInt(txtRoupaOpcao3.getText().toString().split(" ")[1]));
        startActivity(intent);
    }

    @OnClick(R.id.imgRoupaOpcao4)
    public void opcao4(View view) {
        Intent intent = new Intent(this, CompraActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("roupaId", roupaId4);
        intent.putExtra("preco", Integer.parseInt(txtRoupaOpcao4.getText().toString().split(" ")[1]));
        startActivity(intent);
    }

    @OnClick(R.id.imgRoupaOpcao5)
    public void opcao5(View view) {
        Intent intent = new Intent(this, CompraActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("roupaId", roupaId5);
        intent.putExtra("preco", Integer.parseInt(txtRoupaOpcao5.getText().toString().split(" ")[1]));
        startActivity(intent);
    }

    @OnClick(R.id.imgRoupaOpcao6)
    public void opcao6(View view) {
        Intent intent = new Intent(this, CompraActivity.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("roupaId", roupaId6);
        intent.putExtra("preco", Integer.parseInt(txtRoupaOpcao6.getText().toString().split(" ")[1]));
        startActivity(intent);
    }

    @OnClick(R.id.imgOpcaoCamisa)
    public void abrirCamisa(View view) {
        carregarImagens(1);
    }

    @OnClick(R.id.txtOpcaoCamisa)
    public void abrirCamisa2(View view) {
        carregarImagens(1);
    }

    @OnClick(R.id.imgOpcaoCalca)
    public void abrirCalca(View view) {
        carregarImagens(2);
    }

    @OnClick(R.id.txtOpcaoCalca)
    public void abrirCalca2(View view) {
        carregarImagens(2);
    }

    @OnClick(R.id.imgOpcaoSapato)
    public void abrirSapato(View view) {
        carregarImagens(3);
    }

    @OnClick(R.id.txtOpcaoSapato)
    public void abrirSapato2(View view) {
        carregarImagens(3);
    }

    @OnClick(R.id.imgOpcaoOculos)
    public void abrirOculos(View view) {
        carregarImagens(4);
    }

    @OnClick(R.id.txtOpcaoOculos)
    public void abrirOculos2(View view) {
        carregarImagens(4);
    }

    @OnClick(R.id.txtGuardaRoupa)
    public void abrirGuardaRoupa(View view) {
        Intent intent = new Intent(this, GuardaRoupaActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imgGuardaRoupa)
    public void abrirGuardaRoupa2(View view) {
        Intent intent = new Intent(this, GuardaRoupaActivity.class);
        startActivity(intent);
        finish();
    }

    private  void carregarImagens(final int opcao){

        if(userDB.getGender() != "0"){
            if(opcao == 1) {
                imgRoupaOpcao1.setImageResource(R.drawable.camisa_curta_preta);
                txtRoupaOpcao1.setText("$ 10");
                roupaId1 = 1;
                imgRoupaOpcao2.setImageResource(R.drawable.camisa_curta_rosa);
                txtRoupaOpcao2.setText("$ 20");
                roupaId2 = 2;
                imgRoupaOpcao3.setImageResource(R.drawable.camisa_curta_verde);
                txtRoupaOpcao3.setText("$ 30");
                roupaId3 = 3;
                imgRoupaOpcao4.setImageResource(R.drawable.camisa_curta_amarela);
                txtRoupaOpcao4.setText("$ 40");
                roupaId4 = 4;
                imgRoupaOpcao5.setImageResource(R.drawable.camisa_curta_azul);
                txtRoupaOpcao5.setText("$ 50");
                roupaId5 = 5;
                imgRoupaOpcao6.setImageResource(R.drawable.camisa_curta_vermelha);
                txtRoupaOpcao6.setText("$ 60");
                roupaId6 = 6;
                tipo = 1;
            } else if(opcao == 2) {
                imgRoupaOpcao1.setImageResource(R.drawable.calca_azul);
                txtRoupaOpcao1.setText("$ 10");
                roupaId1 = 7;
                imgRoupaOpcao2.setImageResource(R.drawable.calca_cinza);
                txtRoupaOpcao2.setText("$ 20");
                roupaId2 = 8;
                imgRoupaOpcao3.setImageResource(R.drawable.calca_vermelha);
                txtRoupaOpcao3.setText("$ 30");
                roupaId3 = 9;
                imgRoupaOpcao4.setImageResource(R.drawable.calca_preta);
                txtRoupaOpcao4.setText("$ 40");
                roupaId4 = 10;
                imgRoupaOpcao5.setImageResource(R.drawable.calca_verde);
                txtRoupaOpcao5.setText("$ 50");
                roupaId5 = 11;
                imgRoupaOpcao6.setImageResource(R.drawable.calca_vinho);
                txtRoupaOpcao6.setText("$ 60");
                roupaId6 = 12;
                tipo = 2;
            } else if(opcao == 3) {
                imgRoupaOpcao1.setImageResource(R.drawable.sapato_azul);
                txtRoupaOpcao1.setText("$ 10");
                roupaId1 = 13;
                imgRoupaOpcao2.setImageResource(R.drawable.sapato_cinza);
                txtRoupaOpcao2.setText("$ 20");
                roupaId2 = 14;
                imgRoupaOpcao3.setImageResource(R.drawable.sapato_rosa);
                txtRoupaOpcao3.setText("$ 30");
                roupaId3 = 15;
                imgRoupaOpcao4.setImageResource(R.drawable.sapato_azul);
                txtRoupaOpcao4.setText("$ 40");
                roupaId4 = 16;
                imgRoupaOpcao5.setImageResource(R.drawable.sapato_cinza);
                txtRoupaOpcao5.setText("$ 50");
                roupaId5 = 17;
                imgRoupaOpcao6.setImageResource(R.drawable.sapato_rosa);
                txtRoupaOpcao6.setText("$ 60");
                roupaId6 = 18;
                tipo = 3;
            }
        } else {
            if(opcao == 1) {
                imgRoupaOpcao1.setImageResource(R.drawable.camisa_curta_preta);
                txtRoupaOpcao1.setText("$ 10");
                roupaId1 = 1;
                imgRoupaOpcao2.setImageResource(R.drawable.camisa_curta_rosa);
                txtRoupaOpcao2.setText("$ 20");
                roupaId2 = 2;
                imgRoupaOpcao3.setImageResource(R.drawable.camisa_curta_verde);
                txtRoupaOpcao3.setText("$ 30");
                roupaId3 = 3;
                imgRoupaOpcao4.setImageResource(R.drawable.camisa_curta_amarela);
                txtRoupaOpcao4.setText("$ 40");
                roupaId4 = 4;
                imgRoupaOpcao5.setImageResource(R.drawable.camisa_curta_azul);
                txtRoupaOpcao5.setText("$ 50");
                roupaId5 = 5;
                imgRoupaOpcao6.setImageResource(R.drawable.camisa_curta_vermelha);
                txtRoupaOpcao6.setText("$ 60");
                roupaId6 = 6;
                tipo = 1;
            } else if(opcao == 2) {
                imgRoupaOpcao1.setImageResource(R.drawable.calca_azul);
                txtRoupaOpcao1.setText("$ 10");
                roupaId1 = 7;
                imgRoupaOpcao2.setImageResource(R.drawable.calca_cinza);
                txtRoupaOpcao2.setText("$ 20");
                roupaId2 = 8;
                imgRoupaOpcao3.setImageResource(R.drawable.calca_vermelha);
                txtRoupaOpcao3.setText("$ 30");
                roupaId3 = 9;
                imgRoupaOpcao4.setImageResource(R.drawable.calca_preta);
                txtRoupaOpcao4.setText("$ 40");
                roupaId4 = 10;
                imgRoupaOpcao5.setImageResource(R.drawable.calca_verde);
                txtRoupaOpcao5.setText("$ 50");
                roupaId5 = 11;
                imgRoupaOpcao6.setImageResource(R.drawable.calca_vinho);
                txtRoupaOpcao6.setText("$ 60");
                roupaId6 = 12;
                tipo = 2;
            } else if(opcao == 3) {
                imgRoupaOpcao1.setImageResource(R.drawable.sapato_azul);
                txtRoupaOpcao1.setText("$ 10");
                roupaId1 = 13;
                imgRoupaOpcao2.setImageResource(R.drawable.sapato_cinza);
                txtRoupaOpcao2.setText("$ 20");
                roupaId2 = 14;
                imgRoupaOpcao3.setImageResource(R.drawable.sapato_rosa);
                txtRoupaOpcao3.setText("$ 30");
                roupaId3 = 15;
                imgRoupaOpcao4.setImageResource(R.drawable.sapato_azul);
                txtRoupaOpcao4.setText("$ 40");
                roupaId4 = 16;
                imgRoupaOpcao5.setImageResource(R.drawable.sapato_cinza);
                txtRoupaOpcao5.setText("$ 50");
                roupaId5 = 17;
                imgRoupaOpcao6.setImageResource(R.drawable.sapato_rosa);
                txtRoupaOpcao6.setText("$ 60");
                roupaId6 = 18;
                tipo = 3;
            }
        }
    }
}
