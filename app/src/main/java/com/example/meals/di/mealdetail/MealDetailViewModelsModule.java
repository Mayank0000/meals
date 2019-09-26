package com.example.meals.di.mealdetail;

import androidx.lifecycle.ViewModel;

import com.example.meals.di.ViewModelKey;
import com.example.meals.ui.details.MealDetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MealDetailViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MealDetailViewModel.class)
    public abstract ViewModel bindAuthViewModel(MealDetailViewModel viewModel);

}
