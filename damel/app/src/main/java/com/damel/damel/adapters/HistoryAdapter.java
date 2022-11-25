package com.damel.damel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.damel.damel.R;
import com.damel.damel.models.History;

import kotlin.reflect.KVisibility;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final History[] histories;
    private LayoutInflater inflater;
    private Context context;
    public HistoryAdapter(Context context, History[] histories) {
        this.histories = histories;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImageProfile().setImageResource(histories[position].image_profile);
        holder.getNameWorker().setText(histories[position].name_worker);
        holder.getNumRate().setText(histories[position].num_rate);
        holder.getNameJob().setText(histories[position].name_job);
        holder.getPay().setText(histories[position].pay);
        holder.getLengthWork().setText(histories[position].length_work);
    }

    @Override
    public int getItemCount() {
        return histories.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_profile;
        public TextView name_worker;
        public TextView num_rate;
        public TextView name_job;
        public TextView pay;
        public TextClock length_work;
        public ImageButton btn_showdetail;
        public RelativeLayout detail_section;
        boolean toogle_show = false;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            image_profile = itemView.findViewById(R.id.image);
            name_worker = itemView.findViewById(R.id.name);
            num_rate = itemView.findViewById(R.id.num_rating);
            name_job = itemView.findViewById(R.id.name_job);
            pay = itemView.findViewById(R.id.pay);
            length_work = itemView.findViewById(R.id.length_work);
            detail_section = itemView.findViewById(R.id.detail_section);
            btn_showdetail = itemView.findViewById(R.id.btn_showdetail);
            btn_showdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toogle_show = !toogle_show;
                    if (toogle_show){
                        detail_section.setVisibility(View.VISIBLE);
                    } else {
                        detail_section.setVisibility(View.GONE);
                    }
                }
            });

        }


        public ImageView getImageProfile() {
            return image_profile;
        }
        public TextView getNameWorker(){
            return name_worker;
        }
        public TextView getNumRate(){
            return num_rate;
        }
        public  TextView getNameJob(){
            return name_job;
        }
        public TextView getPay(){
            return pay;
        }
        public TextClock getLengthWork(){
            return length_work;
        }
    }
}
