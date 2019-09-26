package com.example.meals.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.meals.model.MealsList;
import com.example.meals.util.Repository;

import javax.inject.Inject;

public class MealDetailViewModel extends ViewModel {

    private static final String TAG = "DetailsViewModel";
    private Repository repository;
    private MediatorLiveData<MealsList> cachedUser = new MediatorLiveData<>();

    @Inject
    public MealDetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getMealDetailList(String id){

        final LiveData<MealsList> source= repository.getMealDetails(id);

        cachedUser.setValue((MealsList) null);
        cachedUser.addSource(source, new Observer<MealsList>() {
            @Override
            public void onChanged(MealsList userSendMessageResource) {
                cachedUser.setValue(userSendMessageResource);
                cachedUser.removeSource(source);
            }
        });
    }

    public LiveData<MealsList> getCategoryLiveDataList(){
        return cachedUser;
    }

}

