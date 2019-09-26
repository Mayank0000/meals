package com.example.meals.network;

import com.example.meals.model.Categories;
import com.example.meals.model.MealsList;
//import com.example.search.model.Details;
//import com.example.search.model.Search;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsApi {

    @GET("1/categories.php")
    Flowable<Categories> getSearchList();

    @GET("1/filter.php")
    Flowable<MealsList> getMealsList(@Query("c") String categoryName);

    @GET("1/lookup.php")
    Flowable<MealsList> getMealDetails(@Query("i") String mealName);

    @GET("1/search.php")
    Flowable<MealsList> getSearchMeal(@Query("s") String mealName);

    @GET("1/random.php")
    Flowable<MealsList> getRandomMeal();

}
