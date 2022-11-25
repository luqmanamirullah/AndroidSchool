package com.damel.damel.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damel.damel.R;
import com.damel.damel.adapters.ChatAdapter;
import com.damel.damel.models.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Chat> userlist = new ArrayList<>();
    ChatAdapter chatAdapter;
    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.setTitle("Pesan");
        actionBar.show();

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        /*RecycleView for Chat*/
        userlist.add(new Chat("Ujang Jabrig", "Saya sudah di depan rumah bu", "10.48 AM", R.drawable.pp1));
        userlist.add(new Chat("Usman Karawita", "Jangan lupa rating nya ya bu😊", "08.08 AM", R.drawable.pp2));
        userlist.add(new Chat("Putri Azza", "Baik bu", "07.48 AM", R.drawable.pp3));
        userlist.add(new Chat("Heni Murtanti", "Terimakasih bu", "08.48 PM", R.drawable.pp4));
        userlist.add(new Chat("Bobi", "Terimakasih bu", "15.48 AM", R.drawable.pp5));
        userlist.add(new Chat("Ujang Jabrig", "Saya sudah di depan rumah bu", "10.48 AM", R.drawable.pp1));
        userlist.add(new Chat("Usman Karawita", "Jangan lupa rating nya ya bu😊", "08.08 AM", R.drawable.pp2));
        userlist.add(new Chat("Putri Azza", "Baik bu", "07.48 AM", R.drawable.pp3));
        userlist.add(new Chat("Heni Murtanti", "Terimakasih bu", "08.48 PM", R.drawable.pp4));
        userlist.add(new Chat("Bobi", "Terimakasih bu", "15.48 AM", R.drawable.pp5));

        recyclerView = view.findViewById(R.id.rv_chat);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(userlist);
        recyclerView.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();

        return view;
    }

}
