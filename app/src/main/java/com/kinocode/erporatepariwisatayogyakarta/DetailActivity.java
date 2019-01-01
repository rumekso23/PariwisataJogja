package com.kinocode.erporatepariwisatayogyakarta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kinocode.erporatepariwisatayogyakarta.api.APIClient;
import com.kinocode.erporatepariwisatayogyakarta.api.APIInterface;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.img_iconWisata) ImageView imgIconWisata;
    @BindView(R.id.tv_namaWisataDetail) TextView tvNamaWisata;
    @BindView(R.id.tv_alamatWisataDetail) TextView tvAlamatWisata;
    @BindView(R.id.tv_detailWisata) JustifiedTextView tvDetailWisata;
    @BindView(R.id.toolbar_detail) Toolbar detailToolbar;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        ButterKnife.bind(this);
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("nama"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        //parsing data dari recylerview ke halaman detai
        tvNamaWisata.setText(intent.getStringExtra("nama"));
        tvAlamatWisata.setText("Lokasi :  " + intent.getStringExtra("alamat"));
        tvDetailWisata.setText(intent.getStringExtra("detail"));
        Glide.with(this)
                .load(intent.getStringExtra("icon"))
                .into(imgIconWisata);


    }
}
