package br.com.eduardotanaka.knowdown;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.eduardotanaka.knowdown.model.GuardaRoupa;
import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import br.com.eduardotanaka.knowdown.widget.Sync;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompraActivity extends AppCompatActivity {

    @BindView(R.id.imgCompra)
    ImageView imgCompra;

    @BindView(R.id.txtPreco)
    TextView txtPreco;

    @BindView(R.id.txtPontosCompra)
    TextView txtPontosCompra;

    private Bundle bundle;
    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontosCompra.setText(userDB.getCoin()+"");

                    bundle = getIntent().getExtras();

                    if(userDB.getGender() != "0"){
                        if(bundle.getInt("roupaId") == 1){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_preta);
                        } else if(bundle.getInt("roupaId") == 2){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_rosa);
                        } else if(bundle.getInt("roupaId") == 3){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_verde);
                        } else if(bundle.getInt("roupaId") == 4){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_amarela);
                        } else if(bundle.getInt("roupaId") == 5){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_azul);
                        } else if(bundle.getInt("roupaId") == 6){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_vermelha);
                        } else if(bundle.getInt("roupaId") == 7){
                            imgCompra.setBackgroundResource(R.drawable.calca_azul);
                        } else if(bundle.getInt("roupaId") == 8){
                            imgCompra.setBackgroundResource(R.drawable.calca_cinza);
                        } else if(bundle.getInt("roupaId") == 9){
                            imgCompra.setBackgroundResource(R.drawable.calca_vermelha);
                        } else if(bundle.getInt("roupaId") == 10){
                            imgCompra.setBackgroundResource(R.drawable.calca_preta);
                        } else if(bundle.getInt("roupaId") == 11){
                            imgCompra.setBackgroundResource(R.drawable.calca_verde);
                        } else if(bundle.getInt("roupaId") == 12){
                            imgCompra.setBackgroundResource(R.drawable.calca_vinho);
                        } else if(bundle.getInt("roupaId") == 13){
                            imgCompra.setBackgroundResource(R.drawable.sapato_azul);
                        } else if(bundle.getInt("roupaId") == 14){
                            imgCompra.setBackgroundResource(R.drawable.sapato_cinza);
                        } else if(bundle.getInt("roupaId") == 15){
                            imgCompra.setBackgroundResource(R.drawable.sapato_rosa);
                        } else if(bundle.getInt("roupaId") == 16){
                            imgCompra.setBackgroundResource(R.drawable.sapato_azul);
                        } else if(bundle.getInt("roupaId") == 17){
                            imgCompra.setBackgroundResource(R.drawable.sapato_cinza);
                        } else if(bundle.getInt("roupaId") == 18){
                            imgCompra.setBackgroundResource(R.drawable.sapato_rosa);
                        }
                    } else {
                        if(bundle.getInt("roupaId") == 1){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_preta);
                        } else if(bundle.getInt("roupaId") == 2){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_rosa);
                        } else if(bundle.getInt("roupaId") == 3){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_verde);
                        } else if(bundle.getInt("roupaId") == 4){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_amarela);
                        } else if(bundle.getInt("roupaId") == 5){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_azul);
                        } else if(bundle.getInt("roupaId") == 6){
                            imgCompra.setBackgroundResource(R.drawable.camisa_curta_vermelha);
                        } else if(bundle.getInt("roupaId") == 7){
                            imgCompra.setBackgroundResource(R.drawable.calca_azul);
                        } else if(bundle.getInt("roupaId") == 8){
                            imgCompra.setBackgroundResource(R.drawable.calca_cinza);
                        } else if(bundle.getInt("roupaId") == 9){
                            imgCompra.setBackgroundResource(R.drawable.calca_vermelha);
                        } else if(bundle.getInt("roupaId") == 10){
                            imgCompra.setBackgroundResource(R.drawable.calca_preta);
                        } else if(bundle.getInt("roupaId") == 11){
                            imgCompra.setBackgroundResource(R.drawable.calca_verde);
                        } else if(bundle.getInt("roupaId") == 12){
                            imgCompra.setBackgroundResource(R.drawable.calca_vinho);
                        } else if(bundle.getInt("roupaId") == 13){
                            imgCompra.setBackgroundResource(R.drawable.sapato_azul);
                        } else if(bundle.getInt("roupaId") == 14){
                            imgCompra.setBackgroundResource(R.drawable.sapato_cinza);
                        } else if(bundle.getInt("roupaId") == 15){
                            imgCompra.setBackgroundResource(R.drawable.sapato_rosa);
                        } else if(bundle.getInt("roupaId") == 16){
                            imgCompra.setBackgroundResource(R.drawable.sapato_azul);
                        } else if(bundle.getInt("roupaId") == 17){
                            imgCompra.setBackgroundResource(R.drawable.sapato_cinza);
                        } else if(bundle.getInt("roupaId") == 18){
                            imgCompra.setBackgroundResource(R.drawable.sapato_rosa);
                        }
                    }

                    txtPreco.setText("$ " + bundle.get("preco").toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }

    @OnClick(R.id.btnConfirmarCompra)
    public void confirmarCompra(View view) {
        if(bundle.getInt("preco") > userDB.getCoin()) {
            Intent intent = new Intent(this, DinheiroInsuficienteActivity.class);
            startActivity(intent);
            finish();
        } else {
            int dinheiro = userDB.getCoin() - bundle.getInt("preco");

            List<GuardaRoupa> roupas;
            if(userDB.getGuardaroupa() == null) {
                roupas = new ArrayList<>();
            } else {
                roupas = userDB.getGuardaroupa();
            }

            boolean adicionar = true;
            for(GuardaRoupa r : roupas){
                if(r.getRoupa() == bundle.getInt("roupaId")){
                    adicionar = false;
                    break;
                }
            }
            if(adicionar){
                userDB.setCoin(dinheiro);
                userDB.updateDB();

                GuardaRoupa gr = new GuardaRoupa();
                gr.setRoupa(bundle.getInt("roupaId"));
                gr.setId(bundle.getInt("tipo"));
                roupas.add(gr);

                mDatabase.child("users").child(userDB.getId()).child("guardaroupa").setValue(roupas);

                Toast.makeText(getApplicationContext(), getString(R.string.roupa_comprada), Toast.LENGTH_LONG).show();

                Sync.start(this);

                Intent intent = new Intent(CompraActivity.this, GuardaRoupaActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.roupa_ja_comprada), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
