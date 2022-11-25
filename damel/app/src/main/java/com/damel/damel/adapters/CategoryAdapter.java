package com.damel.damel.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.damel.damel.R;
import com.damel.damel.models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Category[] categories;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, Category[] categories) {
        this.inflater = LayoutInflater.from(context);
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.getTvNameCategory().setText(categories[position].name);
        holder.getImageCategory().setImageResource(categories[position].image);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNameCategory;
        private final ImageView imageCategory;

        public ViewHolder(View view) {
            super(view);
            tvNameCategory = view.findViewById(R.id.category_name);
            imageCategory = view.findViewById(R.id.category_image);
        }

        public TextView getTvNameCategory() {
            return tvNameCategory;
        }
        public ImageView getImageCategory() {
            return imageCategory;
        }
    }
}
