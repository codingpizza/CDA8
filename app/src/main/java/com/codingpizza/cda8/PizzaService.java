package com.codingpizza.cda8;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PizzaService {
    @GET("/pizzas")
    Call<List<Pizza>> listPizzas();
}
