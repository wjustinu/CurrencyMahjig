package com.example.ticklefairy.currencymahjig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    public String fromCurr;
    private String toCurr;

    public String rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sampleTextView = (TextView) findViewById(R.id.sampleTextView);

        // create a converter object - this connects to the internet and gets a currency data object used to do conversions
        CurrencyConverter converter = new CurrencyConverter();

        converter.getLatest() // get the latest CurrencyData
                .enqueue( // this request happens on the background so we need to put the response in a queue
                        new Callback<CurrencyData>() {
                            @Override
                            public void onResponse(Response<CurrencyData> response, Retrofit retrofit) {
                                // when successful, we use the data here
                                CurrencyData data = response.body();
                                //sampleTextView.setText(data.toString());
                                rate = data.toString();

                                implementSpinner();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                // if something messed up, display the message
                                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //protected void onResume(){
    //    super.onResume();
   //     implementSpinner();
    //}

    public void implementSpinner(){
        Spinner spinFrom = (Spinner)findViewById(R.id.from_spin);
        Spinner spinTo = (Spinner)findViewById(R.id.to_spin);
        String[] base = {"USD"};
        String[] separate= rate.split(",");

        String[] countrylist = {separate[0],separate[1],separate[2],separate[3],separate[4],separate[5],separate[6],separate[7]};
       // Toast.makeText(MainActivity.this, BASE+"hello", Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adaptFrom = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, base);
        ArrayAdapter<String> adaptTo = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, countrylist);

        spinFrom.setAdapter(adaptFrom);
        spinTo.setAdapter(adaptTo);
        spinTo.setOnItemSelectedListener(new ItemSelectedFrom());
        spinTo.setOnItemSelectedListener(new ItemSelectedTo());
    }

    private class ItemSelectedFrom implements AdapterView.OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> av){

        }
        public void onItemSelected(AdapterView<?> av, View view, int position, long id){
            TextView sel=(TextView)view;
            String from=sel.getText().toString();
            fromCurr=from; //capture the currency of the From side


        }
    }

    private class ItemSelectedTo implements AdapterView.OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> av){

        }
        public void onItemSelected(AdapterView<?> av, View view, int position, long id){
            TextView sel=(TextView)view;
            String to=sel.getText().toString();
            toCurr=to; //capture the currency of the To side

        }
    }

    //public String getBase(){
     //   return fromCurr;
  //  } //supposed to get base (fromCurr) of choice from "To Currency" but had problems changing url

    public void showconversion(View view) {
        double ratedouble;
        double value;
        double convertedvalue;
        EditText editvalue;
        String stringvalue;
        TextView changeresult = (TextView)findViewById(R.id.result);

        String countryratestring = toCurr.substring(4);
        //Toast.makeText(MainActivity.this, countryratestring, Toast.LENGTH_SHORT).show();
        ratedouble = Double.valueOf(countryratestring);
        editvalue = (EditText) findViewById(R.id.amount);

        if(editvalue.length()== 0)
        {
            Toast.makeText(MainActivity.this, "Please Enter a Value or you'll crash my program", Toast.LENGTH_SHORT).show();
        }

        else {


            stringvalue = editvalue.getText().toString();
            value = Double.valueOf(stringvalue);
            convertedvalue = value * ratedouble;

            //stringvalue = String.valueOf(convertedvalue);
            stringvalue = String.format("%.2f", convertedvalue);


            if(toCurr.contains("AUD"))
            changeresult.setText("$ " + stringvalue);

            else if(toCurr.contains("CAD"))
                changeresult.setText("$ "+stringvalue);

            else if(toCurr.contains("EUR"))
                changeresult.setText("€ "+stringvalue);

            else if(toCurr.contains("RUB"))
                changeresult.setText("\u20BD "+stringvalue);

            else if(toCurr.contains("HKD"))
                changeresult.setText("HK$ "+stringvalue);

            else if(toCurr.contains("JPY"))
                changeresult.setText("¥ "+stringvalue);

            else if(toCurr.contains("KRW"))
                changeresult.setText("₩ "+stringvalue);

            else
                changeresult.setText("₱ "+stringvalue);
        }
    }
}
