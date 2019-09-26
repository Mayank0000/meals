package com.example.meals.ui.meallist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meals.model.Categories;
import com.example.meals.model.MealsList;
import com.example.meals.util.Repository;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MealListViewModel extends ViewModel {

    private static final String TAG = "MealListViewModel";
    private Repository repository;
    private MediatorLiveData<Categories> cachedUser = new MediatorLiveData<>();
    private MediatorLiveData<MealsList> mealsListMediatorLiveData = new MediatorLiveData<>();
    private MediatorLiveData<MealsList> randomListMediatorLiveData = new MediatorLiveData<>();


    @Inject
    public MealListViewModel(Repository repository) {
        this.repository = repository;
        getCategoryList();
        getRandomList();
    }

//    private MediatorLiveData<MealsList> searchListMediatorLiveData = new MediatorLiveData<>();
//    public void getSearchMealList(String id){
//
//        final LiveData<MealsList> source= repository.getMealDetails(id);
//
//        searchListMediatorLiveData.setValue( null);
//        searchListMediatorLiveData.addSource(source, new Observer<MealsList>() {
//            @Override
//            public void onChanged(MealsList userSendMessageResource) {
//                searchListMediatorLiveData.setValue(userSendMessageResource);
//                searchListMediatorLiveData.removeSource(source);
//            }
//        });
//    }
//
//    public LiveData<MealsList> getMealSearchLiveDataList(){
//        return searchListMediatorLiveData;
//    }


    public void getCategoryList(){
        final LiveData<Categories> source = getLiveDataList();
        cachedUser.setValue(null);
        cachedUser.addSource(source, userSendMessageResource -> {
            cachedUser.setValue(userSendMessageResource);
            cachedUser.removeSource(source);
        });
    }

    public void getRandomList(){
        final LiveData<MealsList> source2 = repository.getRandomMeal();
        randomListMediatorLiveData.setValue(null);
        randomListMediatorLiveData.addSource(source2, mealsList -> {
            randomListMediatorLiveData.setValue(mealsList);
            randomListMediatorLiveData.removeSource(source2);

        });
    }


    public void callMealList(String category){
        final LiveData<MealsList> source = repository.getMealList(category);
        mealsListMediatorLiveData.setValue(null);
        mealsListMediatorLiveData.addSource(source, userSendMessageResource -> {
            mealsListMediatorLiveData.setValue(userSendMessageResource);
            mealsListMediatorLiveData.removeSource(source);
        });

    }

    public LiveData<Categories> getCategoryLiveDataList(){
        return cachedUser;
    }


    public LiveData<MealsList> getMealLiveDataList(){
        return mealsListMediatorLiveData;
    }
    public LiveData<MealsList> getRandomLiveDataList(){
        return randomListMediatorLiveData;
    }


    private LiveData<Categories> getLiveDataList(){

        return LiveDataReactiveStreams.fromPublisher(
                repository.getSearchResult()
                        // instead of calling onError, do this
                        .onErrorReturn(new Function<Throwable,Categories>() {
                            @Override
                            public Categories apply(Throwable throwable) throws Exception {
                                Categories categories = new Categories();
                                categories.setCategories(null);
                                return categories;
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

}


