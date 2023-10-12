package com.meow.assigment3;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView txt_cases, txt_recovered, txt_deaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_cases = findViewById(R.id.jumlah_cases);
        txt_recovered = findViewById(R.id.jumlah_recovered);
        txt_deaths = findViewById(R.id.jumlah_deaths);

        getCovidData();
    }

    private void getCovidData(){
        String URL = "https://651ebf3344a3a8aa4768ed57.mockapi.io/api/mocksample/covid-info";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray.length() > 0) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            String cases = jsonObject.getString("casses");
                            String recovered = jsonObject.getString("recovered");
                            String deaths = jsonObject.getString("deaths");

                            txt_cases.setText(cases);
                            txt_recovered.setText(recovered);
                            txt_deaths.setText(deaths);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

