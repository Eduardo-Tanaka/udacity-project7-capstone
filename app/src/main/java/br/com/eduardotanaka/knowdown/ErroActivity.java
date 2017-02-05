package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErroActivity extends AppCompatActivity {

    @BindView(R.id.txtErroFeedback)
    TextView txtFeedback;

    @BindView(R.id.txtPontosErro)
    TextView txtPontosErro;

    @BindView(R.id.txtAvatarErro)
    TextView txtAvatarErro;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erro);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        txtFeedback.setText(bundle.getString("feedback").toString().trim());

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontosErro.setText(String.valueOf(userDB.getScore()));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }

    @OnClick(R.id.btnJogarErro)
    public void jogar(View view) {
        Random r = new Random();
        int n = r.nextInt(2);
        Intent intent;
        if(n == 1){
            intent = new Intent(this, Exercicio1Activity.class);
        } else {
            intent = new Intent(this, Exercicio5Activity.class);
        }

        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imgAvatarErro)
    public void avatar(View view) {
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txtAvatarErro)
    public void avatar1(View view) {
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
        finish();
    }

}
