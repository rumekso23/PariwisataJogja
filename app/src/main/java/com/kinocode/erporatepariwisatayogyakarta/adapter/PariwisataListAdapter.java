package com.kinocode.erporatepariwisatayogyakarta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kinocode.erporatepariwisatayogyakarta.DetailActivity;
import com.kinocode.erporatepariwisatayogyakarta.R;
import com.kinocode.erporatepariwisatayogyakarta.model.Pariwisata;

import java.util.List;

public class PariwisataListAdapter extends RecyclerView.Adapter<PariwisataListAdapter.MyViewHolder> {

    private List<Pariwisata.Datum> datum;
    private Context context;

    public PariwisataListAdapter(Context context, List<Pariwisata.Datum> datum){
        this.datum = datum;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pariwisata, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final Pariwisata.Datum dataWisata = datum.get(position);
        myViewHolder.tvNamaWisata.setText(dataWisata.getNamaPariwisata());
        Glide.with(context)
                .load(dataWisata.getGambarPariwisata())
                .into(myViewHolder.imgIconWisata);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("nama",dataWisata.getNamaPariwisata());
                intent.putExtra("alamat", dataWisata.getAlamatPariwisata());
                intent.putExtra("detail", dataWisata.getDetailPariwisata());
                intent.putExtra("icon", dataWisata.getGambarPariwisata());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIconWisata;
        TextView tvNamaWisata;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIconWisata = itemView.findViewById(R.id.img_pariwisata);
            tvNamaWisata = itemView.findViewById(R.id.tv_namaWisata);
        }
    }
}
