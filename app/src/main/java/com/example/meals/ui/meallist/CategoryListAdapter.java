package com.example.meals.ui.meallist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meals.R;
//import com.example.search.databinding.HorizontalRowLayoutBinding;
//import com.example.search.databinding.RowLayoutBinding;
import com.example.meals.model.Category;
//import com.example.search.model.UserModelClass;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private OnClickCategory onClickCategory;

    public CategoryListAdapter(Context context, OnClickCategory onClickCategory) {
        this.context =context;
        categoryList = new ArrayList<>();
        this.onClickCategory = onClickCategory;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.horizontal_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(context).load(categoryList.get(position).getStrCategoryThumb()).into(holder.imageView);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(view -> onClickCategory.onClick(categoryList.get((int)view.getTag()).getStrCategory()));
    }

    public void setQuizList(List<Category> mQuizList){
        this.categoryList = mQuizList;
        if(mQuizList!=null && mQuizList.size()>0)
            notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageUrl);
        }
    }
}
