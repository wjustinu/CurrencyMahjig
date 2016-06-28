package com.example.ticklefairy.currencymahjig;

/**
 * Created by Justin Wu
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Rates {

    @SerializedName("AUD")
    @Expose
    public Double AUD;
    @SerializedName("CAD")
    @Expose
    public Double CAD;

    @SerializedName("HKD")
    @Expose
    public Double HKD;

    @SerializedName("JPY")
    @Expose
    public Double JPY;
    @SerializedName("KRW")
    @Expose
    public Double KRW;

    @SerializedName("RUB")
    @Expose
    public Double RUB;

    @SerializedName("EUR")
    @Expose
    public Double EUR;

    @SerializedName("PHP")
    @Expose
    public Double PHP;


    // this is just so that we can use .toString() to turn this object into a string so that we can display it
    @Override
    public String toString() {
        return  "AUD=" + AUD +
                ",CAD=" + CAD +
                ",EUR=" + EUR +
                ",RUB=" + RUB +
                ",HKD=" + HKD +
                ",JPY=" + JPY +
                ",KRW=" + KRW +
                ",PHP=" + PHP;
    }
}

