package com.kinocode.erporatepariwisatayogyakarta.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pariwisata {

    @SerializedName("result")
    private String result;
    @SerializedName("data")
    private List<Datum> data = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }



    public class Datum {

        @SerializedName("nama_pariwisata")
        private String namaPariwisata;
        @SerializedName("alamat_pariwisata")
        private String alamatPariwisata;
        @SerializedName("detail_pariwisata")
        private String detailPariwisata;
        @SerializedName("gambar_pariwisata")
        private String gambarPariwisata;

        public Datum(String namaPariwisata, String alamatPariwisata, String detailPariwisata, String gambarPariwisata){
            this.namaPariwisata = namaPariwisata;
            this.alamatPariwisata = alamatPariwisata;
            this.detailPariwisata = detailPariwisata;
            this.gambarPariwisata = gambarPariwisata;

        }

        public String getNamaPariwisata() {
            return namaPariwisata;
        }

        public void setNamaPariwisata(String namaPariwisata) {
            this.namaPariwisata = namaPariwisata;
        }

        public String getAlamatPariwisata() {
            return alamatPariwisata;
        }

        public void setAlamatPariwisata(String alamatPariwisata) {
            this.alamatPariwisata = alamatPariwisata;
        }

        public String getDetailPariwisata() {
            return detailPariwisata;
        }

        public void setDetailPariwisata(String detailPariwisata) {
            this.detailPariwisata = detailPariwisata;
        }

        public String getGambarPariwisata() {
            return gambarPariwisata;
        }

        public void setGambarPariwisata(String gambarPariwisata) {
            this.gambarPariwisata = gambarPariwisata;
        }

    }
}
