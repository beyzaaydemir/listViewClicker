package com.example.beyza.myapplication;

public class Kisiler {

    Veritabani db;
    String ad,soyad,tel,cinsiyet;

    public Kisiler(Kisiler kisi)
    {

    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getTel() {
        return tel;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }
}
