package com.example.ticklefairy.currencymahjig;

/**
 * Created by Justin Wu
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyData {

    // A little GSON Tutorial: (This object is a GSON object... which is just a java object version of JSON data)
    // @SerializedName is the name used in the JSON format
    // @Expose is needed for the data to be taken automatically from the internet and stored into the variable

    @SerializedName("base")
    @Expose
    public String base;

    @SerializedName("rates")
    @Expose
    public Rates rates;


     //this is just so that we can use .toString() to turn this object into a string so that we can display it
   // @Override
     //  public String toString() {
      //return "CurrencyData{" +
        //       "base='" + base + '\'' +
          //      ", rates=" + rates +
            //    '}';
   //}

    @Override
    public String toString() {
        return "" + rates + ',';
    }
}