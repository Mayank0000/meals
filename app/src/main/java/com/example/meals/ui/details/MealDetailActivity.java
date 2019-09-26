package com.example.meals.ui.details;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.meals.R;
import com.example.meals.databinding.ActivityDetailsBinding;
//import com.example.search.model.Details;
import com.example.meals.util.Constants;
import com.example.meals.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MealDetailActivity extends DaggerAppCompatActivity {

    private MealDetailViewModel viewModel;
    @Inject RequestManager requestManager;
    @Inject ViewModelProviderFactory providerFactory;
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        viewModel = ViewModelProviders.of(this, providerFactory).get(MealDetailViewModel.class);
        viewModel.getMealDetailList(getIntent().getStringExtra("id"));
        setProgressBar(true);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getCategoryLiveDataList().observe(this, mealsList -> {
            if (mealsList != null) {
                binding.mealName.setText(mealsList.getMeals().get(0).getStrMeal());
                binding.mealCategory.setText(mealsList.getMeals().get(0).getStrCategory());
                binding.mealArea.setText(mealsList.getMeals().get(0).getStrArea());
                binding.mealtags.setText(mealsList.getMeals().get(0).getStrTags());
                binding.mealInstruction.setText(mealsList.getMeals().get(0).getStrInstructions());
                binding.mealIngredient.setText(mealsList.getMeals().get(0).getStrIngredient1());
                requestManager.load(mealsList.getMeals().get(0).getStrMealThumb()).into(binding.imagePoster);
                setProgressBar(false);
            }else{
                if (!Constants.checkInternetConnection(this)) {
                    Snackbar.make(findViewById(android.R.id.content), Constants.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void setProgressBar(boolean flag) {
        if (flag) {
            binding.mainLayout.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.mainLayout.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}