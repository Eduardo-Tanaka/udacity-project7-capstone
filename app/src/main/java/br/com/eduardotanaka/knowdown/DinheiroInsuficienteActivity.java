package br.com.eduardotanaka.knowdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DinheiroInsuficienteActivity extends AppCompatActivity {

    @BindView(R.id.txtPontosDinheiro)
    TextView txtPontosDinheiro;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinheiro_insuficiente);

        ButterKnife.bind(this);

        mDatabase = LibraryClass.getFirebase();

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase.child("users").child(user.getId()).addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userDB = dataSnapshot.getValue(User.class);
                    txtPontosDinheiro.setText(String.valueOf(userDB.getScore()));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            }
        );
    }
}
