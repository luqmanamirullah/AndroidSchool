package com.damel.damel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.damel.damel.R;
import com.damel.damel.models.Featured;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {

    private final Featured[] featureds;
    private final LayoutInflater inflater;

    public FeaturedAdapter(Context context, Featured[] featureds) {
        this.inflater = LayoutInflater.from(context);
        this.featureds = featureds;
    }

    @NonNull
    @Override
    public FeaturedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_featured, parent, false);
        return new FeaturedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedAdapter.ViewHolder holder, int position) {
        Featured featured = featureds[position];
        ImageView imageView = holder.getIvImage();

        holder.getTvName().setText(featured.title);
        holder.getTvDesc().setText(featured.desc);

        Glide.with(imageView.getContext())
                .load(featured.imageUrl)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return featureds.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvName;
        private final TextView tvDesc;

        public ViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.featured_image);
            tvName = view.findViewById(R.id.featured_name);
            tvDesc = view.findViewById(R.id.featured_desc);
        }

        public ImageView getIvImage() {
            return ivImage;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvDesc() {
            return tvDesc;
        }
    }
}
