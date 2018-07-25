package com.codingpizza.cda8

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callForPizza()
    }

    private fun callForPizza() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val pizzaService = retrofit.create<PizzaService>(PizzaService::class.java)

        val pizzaList = pizzaService.listPizzas()
        pizzaList.enqueue(object : Callback<List<Pizza>> {
            override fun onResponse(call: Call<List<Pizza>>, response: Response<List<Pizza>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: Respuesta Exitosa!")
                    val inmutablePizzaList : List<Pizza>? = response.body()
                    if (inmutablePizzaList != null) {
                        for (pizza in inmutablePizzaList) {
                            pizza.let{
                                Log.d(TAG, "Pizza con el nombre \n " + pizza.name
                                        + "Y descripcion \n " + pizza.description)
                            }
                        }
                    }
                } else {
                    Log.d(TAG, "onResponse: Llamada fallida")
                }
            }

            override fun onFailure(call: Call<List<Pizza>>, t: Throwable) {
                Log.d(TAG, "onFailure: Ha fallado la llamada")
            }
        })

    }

    companion object {

        val BASE_URL = "http://private-3cc5a4-codingpizza.apiary-mock.com"
        private val TAG = "MainActivity"
    }
}
