package br.com.eduardotanaka.knowdown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

import br.com.eduardotanaka.knowdown.adapter.PontuacaoAdapter;
import br.com.eduardotanaka.knowdown.model.User;
import br.com.eduardotanaka.knowdown.util.LibraryClass;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RankingActivity extends AppCompatActivity {

    @BindView(R.id.btnRankingJogar)
    Button btnRankingJogar;

    @BindView(R.id.ranking_progress)
    ProgressBar mProg;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<User> myDataset;

    private DatabaseReference mDatabase;
    private User userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ButterKnife.bind(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.pontuacao_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        User user = new User();
        user.retrieveIdSP(this);

        mDatabase = LibraryClass.getFirebase();

        Query recentPostsQuery = mDatabase.child("users")
                .limitToFirst(100);

        myDataset = new ArrayList<User>();

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User p = new User();
                p.setId(dataSnapshot.getValue(User.class).getId());
                p.setScore(dataSnapshot.getValue(User.class).getScore());
                p.setName(dataSnapshot.getValue(User.class).getName());
                p.setGender(dataSnapshot.getValue(User.class).getGender());
                myDataset.add(p);

                Collections.sort(myDataset);

                mAdapter = new PontuacaoAdapter(getApplicationContext(), myDataset);
                mRecyclerView.setAdapter(mAdapter);

                mProg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mProg.setVisibility(View.VISIBLE);
                int k = 0;
                for(User u : myDataset){
                    if(dataSnapshot.getValue(User.class).getId().equals(u.getId())){
                        User p = new User();
                        p.setId(dataSnapshot.getValue(User.class).getId());
                        p.setScore(dataSnapshot.getValue(User.class).getScore());
                        p.setName(dataSnapshot.getValue(User.class).getName());
                        p.setGender(dataSnapshot.getValue(User.class).getGender());
                        myDataset.set(k, p);
                        break;
                    }
                    k++;
                }

                Collections.sort(myDataset);

                mAdapter = new PontuacaoAdapter(getApplicationContext(), myDataset);
                mRecyclerView.setAdapter(mAdapter);
                mProg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.btnRankingJogar)
    public void jogar(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
