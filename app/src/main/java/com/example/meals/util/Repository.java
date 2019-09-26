package com.example.meals.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import com.example.meals.model.Categories;
import com.example.meals.model.MealsList;
import com.example.meals.network.MealsApi;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


public class Repository {

    private MealsApi mealsApi;

    @Inject
    public Repository(MealsApi mealsApi){
        this.mealsApi = mealsApi;
    }

    public Flowable<Categories> getSearchResult(){
        return mealsApi.getSearchList();
    }



    public LiveData<MealsList> getMealList(String category){
        return LiveDataReactiveStreams.fromPublisher(
                mealsApi.getMealsList(category)
                        .onErrorReturn(throwable -> null)
                        .map(mealList -> mealList)
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<MealsList> getMealDetails(String mealName){
        return LiveDataReactiveStreams.fromPublisher(
                mealsApi.getMealDetails(mealName)
                        .onErrorReturn(throwable -> null)
                        .map(mealList -> mealList)
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<MealsList> getSearchMeal(String mealName){
        return LiveDataReactiveStreams.fromPublisher(
                mealsApi.getSearchMeal(mealName)
                        .onErrorReturn(throwable -> null)
                        .map(mealList -> mealList)
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<MealsList> getRandomMeal(){
        return LiveDataReactiveStreams.fromPublisher(
                mealsApi.getRandomMeal()
                        .onErrorReturn(throwable -> null)
                        .map(mealList -> mealList)
                        .subscribeOn(Schedulers.io()));
    }

}
