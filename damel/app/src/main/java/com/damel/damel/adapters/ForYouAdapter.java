package com.damel.damel.adapters;


import static com.damel.damel.utils.Utils.setMargins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.damel.damel.R;
import com.damel.damel.models.ForYou;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder> {

    private ForYou[] forYous;
    private LayoutInflater inflater;

    public ForYouAdapter(Context context, ForYou[] forYous) {
        this.inflater = LayoutInflater.from(context);
        this.forYous = forYous;
    }

    @NonNull
    @Override
    public ForYouAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_foryou, parent, false);
        return new ForYouAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForYouAdapter.ViewHolder holder, int position) {
        ForYou forYou = forYous[position];
        ImageView imageView = holder.getIvImage();

        if (position == 0) {
            setMargins(holder.getCvForYou(), 10, 0, 0, 0);
        }

        if (position == (forYous.length - 1)) {
            setMargins(holder.getCvForYou(), 0, 0, 10, 0);
        }

        holder.getTvName().setText(forYou.name);
        holder.getTvPeople().setText(forYou.people);
        holder.getTvDistance().setText(forYou.city);
        holder.getTvRating().setText(String.valueOf(forYou.rating).replace(".", ","));
        holder.getIvImage().setImageResource(R.drawable.plumber);
        Glide.with(imageView.getContext())
                .load(forYou.imageUrl)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return forYous.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cvForYou;
        private final TextView tvName;
        private final TextView tvPeople;
        private final ImageView ivImage;
        private final TextView tvDistance;
        private final TextView tvRating;

        public ViewHolder(View view) {
            super(view);
            this.cvForYou = view.findViewById(R.id.foryou_card);
            this.tvName = view.findViewById(R.id.foryou_name);
            this.tvPeople = view.findViewById(R.id.foryou_people);
            this.ivImage = view.findViewById(R.id.foryou_image);
            this.tvDistance = view.findViewById(R.id.foryou_distance);
            this.tvRating = view.findViewById(R.id.foryou_rating);
        }

        public ImageView getIvImage() {
            return ivImage;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvDistance() {
            return tvDistance;
        }

        public TextView getTvRating() {
            return tvRating;
        }

        public CardView getCvForYou() {
            return cvForYou;
        }

        public TextView getTvPeople() {
            return tvPeople;
        }
    }

}