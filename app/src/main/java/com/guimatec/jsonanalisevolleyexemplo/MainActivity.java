package com.guimatec.jsonanalisevolleyexemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonAnalisar = findViewById(R.id.button_analisar);

        mQueue = Volley.newRequestQueue(this);

        Button btnRefazer = findViewById(R.id.btnRefazer);
        btnRefazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewResult.setText("");
            }
        });

        buttonAnalisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonAnalisar();
            }
        });
    }

    private void jsonAnalisar() {

        String url = "http://www.json-generator.com/api/json/get/ceiDHpAJnS?indent=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("admob");

                            for (int i =0; i < jsonArray.length(); i++){
                                JSONObject admob = jsonArray.getJSONObject(i);

                                String nome = admob.getString("nome");
                                String cargo = admob.getString("cargo");
                                int dolar = admob.getInt("dolar");
                                int idade = admob.getInt("idade");
                                int real = admob.getInt("real");
                                mTextViewResult.append(" Nome: "+nome + "\n " +"idade: "+String.valueOf(idade)+"\n" + "Cargo: "+cargo +"\n " +"Saldo em Reais: R$"+String.valueOf(real) + "\n " +"Saldo em dólar: US$"+String.valueOf(dolar) + "\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }
}
