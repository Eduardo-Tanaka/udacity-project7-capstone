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

public class AvatarActivity extends AppCompatActivity {

    @BindView(R.id.txtPontosAvatar)
    TextView txtPontosAvatar;

    @BindView(R.id.lblAcertos)
    TextView lblAcertos;

    @BindView(R.id.lblErros)
    TextView lblErros;

    @BindView(R.id.imgAvatar0)
    ImageView imgAvatar0;

    @BindView(R.id.avatar_camisa)
    ImageView imgCamisa;

    @BindView(R.id.avatar_calca)
    ImageView imgCalca;

    @BindView(R.id.avatar_sapato)
    ImageView imgSapato;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontosAvatar.setText(String.valueOf(userDB.getScore()));
                    lblAcertos.setText(String.valueOf(userDB.getAcerto()));
                    lblErros.setText(String.valueOf(userDB.getErro()));

                    if(userDB.getGender().equals("0") ){
                        imgAvatar0.setImageResource(R.drawable.avatar_menina2_sem_roupa);
                    }else{
                        imgAvatar0.setImageResource(R.drawable.avatar_menino2_sem_roupa);
                    }

                    getRoupaUtilizada();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }

    @OnClick(R.id.imgMontarAvatar)
    public void montarAvatar(View view) {
        Intent intent = new Intent(this, RoupaActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txtMontarAvatar)
    public void montarAvatar2(View view) {
        Intent intent = new Intent(this, RoupaActivity.class);
        startActivity(intent);
        finish();
    }

    private void getRoupaUtilizada(){
        imgCamisa.setImageResource(userDB.getCamisa());
        imgCalca.setImageResource(userDB.getCalca());
        imgSapato.setImageResource(userDB.getSapato());
    }
}
