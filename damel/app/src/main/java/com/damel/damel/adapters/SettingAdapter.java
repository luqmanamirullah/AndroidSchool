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
import com.damel.damel.models.Setting;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private final Setting[] settings;
    private final LayoutInflater inflater;

    public SettingAdapter(Context context, Setting[] settings) {
        this.inflater = LayoutInflater.from(context);
        this.settings = settings;
    }

    @NonNull
    @Override
    public SettingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_setting, parent, false);
        return new SettingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.ViewHolder holder, int position) {
        Setting setting = settings[position];
        holder.getIvIcon().setImageResource(setting.icon);
        holder.getTvTitle().setText(setting.title);
        holder.getTvDesc().setText(setting.desc);
    }

    @Override
    public int getItemCount() {
        return settings.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivIcon;
        private final TextView tvTitle, tvDesc;

        public ViewHolder(View view) {
            super(view);
            this.ivIcon = view.findViewById(R.id.setting_icon);
            this.tvTitle = view.findViewById(R.id.setting_title);
            this.tvDesc = view.findViewById(R.id.setting_desc);
        }


        public ImageView getIvIcon() {
            return ivIcon;
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvDesc() {
            return tvDesc;
        }
    }
}
