package br.com.eduardotanaka.knowdown.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.eduardotanaka.knowdown.R;

public class PontuacaoViewHolder extends RecyclerView.ViewHolder {

    // each data item
    public TextView mTextNome;
    public TextView mTextPontos;
    public ImageView mImagem;
    private Context context;

    public PontuacaoViewHolder(Context c, View v) {
        super(v);
        mTextNome = (TextView) v.findViewById(R.id.txtNomeRanking1);
        mTextPontos = (TextView) v.findViewById(R.id.txtPontosRanking1);
        mImagem = (ImageView) v.findViewById(R.id.imgRanking1);
        context = c;
    }
}
