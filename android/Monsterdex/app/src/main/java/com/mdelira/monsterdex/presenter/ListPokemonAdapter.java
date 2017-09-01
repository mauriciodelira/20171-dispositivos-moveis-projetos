package com.mdelira.monsterdex.presenter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mdelira.monsterdex.R;
import com.mdelira.monsterdex.models.PokemonEntry;

import java.util.ArrayList;

public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    private ArrayList<PokemonEntry> dados;
    private Context context;

    public ListPokemonAdapter(Context context){
        this.dados = new ArrayList<>();
        this.context = context;
    }

    public void addDados(ArrayList<PokemonEntry> novosDados){
        this.dados.addAll(novosDados);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // ao criar essa viewHolder (segurador de view), ele vai criar com o layout abaixo
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // ao setar a view
        PokemonEntry p = dados.get(position);
        holder.pokemonName.setText(p.getNome());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumero() + ".png")
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return this.dados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonNumber, pokemonName, pokemonType1, pokemonType2;

        public ViewHolder(View itemView) {
            super(itemView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.ivListaPokemonImagem);
            pokemonNumber = (TextView) itemView.findViewById(R.id.tvListaPokemonNumero);
            pokemonName = (TextView) itemView.findViewById(R.id.tvListaPokemonNome);
            pokemonType1 = (TextView) itemView.findViewById(R.id.tvListaPokemonTipo1);
            pokemonType2 = (TextView) itemView.findViewById(R.id.tvListaPokemonTipo2);
        }
    }
}
