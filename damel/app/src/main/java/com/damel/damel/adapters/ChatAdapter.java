package com.damel.damel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.damel.damel.R;
import com.damel.damel.models.Chat;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Chat>userlist;

    public ChatAdapter(List<Chat> userlist) {
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = userlist.get(position).getName_person();
        String message = userlist.get(position).getMessage_current();
        String time = userlist.get(position).getTime();
        int image = userlist.get(position).getImage_profile();

        holder.setData(name, message, time, image);
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_profile;
        private TextView message_current;
        private TextView time;
        private CircleImageView image1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_profile = itemView.findViewById(R.id.name_person);
            message_current = itemView.findViewById(R.id.message_current);
            time = itemView.findViewById(R.id.time);
            image1 = itemView.findViewById(R.id.image1);
        }

        public void setData(String name, String massage, String time, int image) {
            name_profile.setText(name);
            message_current.setText(massage);
            this.time.setText(time);
            image1.setImageResource(image);
        }
    }
}
