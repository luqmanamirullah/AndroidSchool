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
import com.damel.damel.models.Employe;

public class EmployeAdapter extends RecyclerView.Adapter<EmployeAdapter.ViewHolder> {
    private Employe[] employes;
    private LayoutInflater inflater;

    public EmployeAdapter(Context context, Employe[] employes) {
        this.inflater = LayoutInflater.from(context);
        this.employes = employes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_employe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView imageView = holder.getJobImage();
        holder.getJobName().setText(employes[position].job_name);
        holder.getJobImage().setImageResource(R.drawable.plumber);
        Glide.with(imageView.getContext())
                .load(employes[position].image)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return employes.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView job_name;
        private  final ImageView job_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            job_name = itemView.findViewById(R.id.job_name);
            job_image = itemView.findViewById(R.id.job_image);
        }

        public TextView getJobName(){
            return  job_name;
        }

        public ImageView getJobImage(){
            return job_image;
        }
    }
}
