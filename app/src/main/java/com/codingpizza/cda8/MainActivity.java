package com.codingpizza.cda8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://private-3cc5a4-codingpizza.apiary-mock.com";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callForPizza();

    }

    private void callForPizza() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PizzaService pizzaService = retrofit.create(PizzaService.class);

        Call<List<Pizza>> pizzaList = pizzaService.listPizzas();
        pizzaList.enqueue(new Callback<List<Pizza>>() {
            @Override
            public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: Respuesta Exitosa!");
                        for (Pizza pizza : response.body()) {
                            Log.d(TAG, "Pizza con el nombre \n "+pizza.getName()
                            + "Y descripcion \n "+pizza.getDescription());
                        }
                } else {
                    Log.d(TAG, "onResponse: Llamada fallida");
                }
            }

            @Override
            public void onFailure(Call<List<Pizza>> call, Throwable t) {
                Log.d(TAG, "onFailure: Ha fallado la llamada");
            }
        });

    }
}

