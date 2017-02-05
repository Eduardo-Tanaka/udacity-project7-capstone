package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.widget.Sync;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, ValueEventListener{

    @BindView(R.id.btnMainJogar)
    Button btnMainJogar;

    @BindView(R.id.btnMainAvatar)
    Button btnMainAvatar;

    @BindView(R.id.btnMainGuardaRoupa)
    Button btnMainGuardaRoupa;

    @BindView(R.id.btnMainComprarRoupa)
    Button btnMainComprarRoupa;

    @BindView(R.id.btnMainRanking)
    Button btnMainRanking;

    @BindView(R.id.txtMainPontos)
    TextView txtMainPontos;

    private User userDB;

    private static final int ID_LOADER = 44;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        User user = new User();
        user.retrieveIdSP(this);

        getSupportLoaderManager().initLoader(ID_LOADER, null, this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);
    }

    @OnClick(R.id.btnMainAvatar)
    public void avatar(View view) {
        startActivity(new Intent(MainActivity.this, AvatarActivity.class));
    }

    @OnClick(R.id.btnMainGuardaRoupa)
    public void guardaroupa(View view) {
        startActivity(new Intent(MainActivity.this, GuardaRoupaActivity.class));
    }

    @OnClick(R.id.btnMainComprarRoupa)
    public void comprarroupa(View view) {
        startActivity(new Intent(MainActivity.this, RoupaActivity.class));
    }

    @OnClick(R.id.btnMainRanking)
    public void ranking(View view) {
        startActivity(new Intent(MainActivity.this, RankingActivity.class));
    }

    @OnClick(R.id.btnMainJogar)
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
    }


    @OnClick(R.id.btnExit)
    public void exit(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.action_play) {
            Random r = new Random();
            int n = r.nextInt(2);
            Intent intent;
            if(n == 1){
                intent = new Intent(this, Exercicio1Activity.class);
            } else {
                intent = new Intent(this, Exercicio5Activity.class);
            }
            startActivity(intent);
        } else if(id == R.id.action_roupa) {
            Intent intent = new Intent(this, RoupaActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_guarda_roupa) {
            Intent intent = new Intent(this, GuardaRoupaActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_ranking) {
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_avatar) {
            Intent intent = new Intent(this, AvatarActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        userDB = dataSnapshot.getValue(User.class);

        txtMainPontos.setText(String.valueOf(userDB.getScore()));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        switch (loaderId) {
            case ID_LOADER:
                userDB = new User();
                userDB.contextDataDB(this);

                Sync.start(this);
                return null;
            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
