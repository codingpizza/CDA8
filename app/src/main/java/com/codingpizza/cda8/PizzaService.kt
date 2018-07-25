package com.codingpizza.cda8

import retrofit2.Call
import retrofit2.http.GET

interface PizzaService {
    @GET("/pizzas")
    fun listPizzas(): Call<List<Pizza>>
}
