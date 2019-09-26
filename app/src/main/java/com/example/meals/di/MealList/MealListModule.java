package com.example.meals.di.MealList;


import android.app.Application;

import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MealListModule {

    @MealListScope
    @Provides
    static PagedList.Config getPageList() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(100).build();
    }

    @MealListScope
    @Provides
    static DividerItemDecoration getDecoration(Application application){
        return new DividerItemDecoration(application, DividerItemDecoration.VERTICAL);
    }

//    @MealListScope
//    @Provides
//    static SearchListAdapter getAdapter(){
//        return new SearchListAdapter();
//}
}


















