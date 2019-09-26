package com.example.meals.ui.meallist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meals.R;
import com.example.meals.databinding.PagingDemoLayoutBinding;
import com.example.meals.model.MealsList;
import com.example.meals.ui.details.MealDetailActivity;
import com.example.meals.util.Constants;
//import com.example.search.model.UserModelClass;
import com.example.meals.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class MealListActivity extends DaggerAppCompatActivity implements OnClickCategory,
        OnClickMealList{

    @Inject ViewModelProviderFactory viewModelFactory;
    @Inject DividerItemDecoration itemDecorator;
    private PageListAdapter pageListAdapter;
    private PagingDemoLayoutBinding binding;
    private MealListViewModel viewModel;
    private CategoryListAdapter categoryListAdapter;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.paging_demo_layout);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MealListViewModel.class);
        //Initialised with 1st list of data
        viewModel.callMealList("Seafood");
        init();
        observed();
    }

    private void init() {
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));

        categoryListAdapter = new CategoryListAdapter(this,this);
        binding.horizontalList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,true));
        binding.horizontalList.setAdapter(categoryListAdapter);

        pageListAdapter = new PageListAdapter(this,this);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(pageListAdapter);
        binding.list.addItemDecoration(itemDecorator);
        binding.list.setHasFixedSize(true);

        if (!Constants.checkInternetConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), Constants.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    private void observed(){

        viewModel.getCategoryLiveDataList().observe(this, categories -> {
            if(categories!=null)
                if(categories.getCategories()!=null) {
                    if(categories.getCategories().size()>0)
                        categoryListAdapter.setQuizList(categories.getCategories());
                }
        });

        viewModel.getRandomLiveDataList().observe(this, new Observer<MealsList>() {
            @Override
            public void onChanged(MealsList mealsList) {
                if(mealsList!=null)
                    pageListAdapter.setCard(mealsList.getMeals());

            }
        });

        viewModel.getMealLiveDataList().observe(this, mealsList -> {
            if(mealsList!=null)
                pageListAdapter.setMealList(mealsList.getMeals());
        });

    }

    @Override
    public void onClick(String name) {
        viewModel.callMealList(name);
    }

    @Override
    public void onMealClick(String id) {
        Intent intent = new Intent(this, MealDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

}
