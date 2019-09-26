package com.example.meals.ui.meallist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meals.R;
import com.example.meals.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class PageListAdapter extends RecyclerView.Adapter{

    private List<Meal> mealsLists;
    private List<Meal> mealsCardLists;
    private Context context;
    private OnClickMealList onClickMealList;

    public PageListAdapter(Context context,OnClickMealList onClickMealList) {
        mealsLists = new ArrayList<>();
        mealsCardLists = new ArrayList<>();
        this.context = context;
        this.onClickMealList = onClickMealList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1){
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.cardview, parent, false);
            return new MyViewHolder2(view);

        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.row_layout, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {
        if(position == 0){
            MyViewHolder2 holder = (MyViewHolder2) holder1;
            Glide.with(context).load(mealsCardLists.get(position).getStrMealThumb()).into(holder.iVImage);
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(view -> onClickMealList.onMealClick(mealsCardLists.get((int)view.getTag()).getIdMeal()));
        }else{
            MyViewHolder holder = (MyViewHolder) holder1;
            holder.tvName.setText(mealsLists.get(position-1).getStrMeal());
            Glide.with(context).load(mealsLists.get(position-1).getStrMealThumb()).into(holder.iVImage);
            holder.itemView.setTag(position-1);
            holder.itemView.setOnClickListener(view -> onClickMealList.onMealClick(mealsLists.get((int)view.getTag()).getIdMeal()));

        }
    }

    public void setMealList(List<Meal> mealsLists){
        this.mealsLists = mealsLists;
        if(mealsLists!=null && mealsLists.size()>0)
            notifyDataSetChanged();
    }


    public void setCard(List<Meal> mealsCardLists){
        this.mealsCardLists = mealsCardLists;
        if(mealsCardLists!=null && mealsCardLists.size() > 0)
            notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return mealsLists.size()+ mealsCardLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;
        protected ImageView iVImage;
        MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            iVImage = itemView.findViewById(R.id.imageUrl);
        }
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
        protected ImageView iVImage;
        MyViewHolder2(View itemView) {
            super(itemView);
            iVImage = itemView.findViewById(R.id.imageUrl);
        }
    }

}
