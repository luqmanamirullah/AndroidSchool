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
import com.damel.damel.adapters.HistoryAdapter;
import com.damel.damel.models.History;

public class HistoryFragment extends Fragment {
    private final History[] histories = new History[5];
    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.setTitle("Riwayat");
        actionBar.show();

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        /*Recycle View for History Detail*/
        histories[0] = new History(R.drawable.pp1, "Ujang Jabrig", "5", "Tukang Ledeng", "RP.100.000", "1.5 jam");
        histories[1] = new History(R.drawable.pp2, "Usman Karawita", "5", "Tukang Kebun", "RP.110.000", "1.5 jam");
        histories[2] = new History(R.drawable.pp3, "Putri Azza", "5", "Cleaning Service", "RP.150.000", "2 jam");
        histories[3] = new History(R.drawable.pp4, "Heni Murtanti", "5", "Baby Shiter", "RP.90.000", "1 jam");
        histories[4] = new History(R.drawable.pp5, "Iwan Kurniawan", "4.5", "Tukang Ledeng", "RP.80.000", "30 menit");
        RecyclerView recyclerView = view.findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        HistoryAdapter historyAdapter = new HistoryAdapter(getContext(), histories);
        recyclerView.setAdapter(historyAdapter);
        return view;
    }

}
