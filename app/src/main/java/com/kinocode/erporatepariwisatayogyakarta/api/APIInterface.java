package com.kinocode.erporatepariwisatayogyakarta.api;

import com.kinocode.erporatepariwisatayogyakarta.model.Pariwisata;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {

    @GET("bootcamp/jsonBootcamp.php")
    Call<Pariwisata> getPariwisataList();
}
