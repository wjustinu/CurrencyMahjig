package com.example.ticklefairy.currencymahjig;

/**
 * Created by Justin Wu
 */

import retrofit.Call;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;



public class CurrencyConverter extends MainActivity{

    public FixerIoService fixerIo;

    public CurrencyConverter() {

        // Toast.makeText(CurrencyConverter.this, getBase(), Toast.LENGTH_SHORT).show();
        // Create a retrofit instance - this is the magical library that gets the JSON data from the internet and turns it into an object for you
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fixerIo = retrofit.create(FixerIoService.class);

    }

    public Call<CurrencyData> getLatest(){
        return fixerIo.getLatest();
    }
    public interface FixerIoService{
//?base=USD

        @GET("/latest?base=USD")


        Call<CurrencyData> getLatest();
    }


}
