package com.example.meals.di.MealList;

import androidx.lifecycle.ViewModel;

import com.example.meals.ui.meallist.MealListViewModel;
import com.example.meals.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MealListModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MealListViewModel.class)
    public abstract ViewModel bindAuthViewModel(MealListViewModel viewModel);

}
