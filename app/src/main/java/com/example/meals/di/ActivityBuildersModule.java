package com.example.meals.di;

import com.example.meals.di.MealList.MealListModule;
import com.example.meals.di.mealdetail.MealDetailModule;
import com.example.meals.di.mealdetail.MealDetailScope;
import com.example.meals.di.mealdetail.MealDetailViewModelsModule;
import com.example.meals.ui.details.MealDetailActivity;
import com.example.meals.ui.meallist.MealListActivity;
import com.example.meals.di.MealList.MealListScope;
import com.example.meals.di.MealList.MealListModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @MealListScope
    @ContributesAndroidInjector(
            modules = {MealListModelsModule.class, MealListModule.class}
    )
    abstract MealListActivity contributeAuthActivity();

    @MealDetailScope
    @ContributesAndroidInjector(
            modules = {MealDetailViewModelsModule.class, MealDetailModule.class}

    )
    abstract MealDetailActivity contributeActivity();

}
