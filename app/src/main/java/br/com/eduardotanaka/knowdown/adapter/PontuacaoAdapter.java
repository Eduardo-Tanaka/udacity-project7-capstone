package br.com.eduardotanaka.knowdown.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.eduardotanaka.knowdown.R;
import br.com.eduardotanaka.knowdown.model.User;

public class PontuacaoAdapter extends RecyclerView.Adapter<PontuacaoViewHolder> {

    private ArrayList<User> mDataset;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public PontuacaoAdapter(Context context, ArrayList<User> myDataset) {
        mDataset = myDataset;
        // Recebe contexto para ser usado no evento de click
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PontuacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pontuacao_adapter_layout, parent, false);

        PontuacaoViewHolder vh = new PontuacaoViewHolder(mContext, v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PontuacaoViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextNome.setText(String.valueOf(mDataset.get(position).getName()));
        holder.mTextPontos.setText(String.valueOf(mDataset.get(position).getScore()));
        if(mDataset.get(position).getGender().equals("1")){
            holder.mImagem.setImageResource(R.drawable.ranking_menino);
        } else {
            holder.mImagem.setImageResource(R.drawable.ranking_menina);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
