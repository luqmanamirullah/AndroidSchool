package com.damel.damel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.damel.damel.R;
import com.damel.damel.adapters.EmployeAdapter;
import com.damel.damel.fragments.HomeFragment;
import com.damel.damel.models.Employe;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private final  int MAX_EMPLOYE = 9;
    private final Employe[] employes = new Employe[MAX_EMPLOYE];
    private SearchView search_bar;
    String[] listJob = {"Tukang Pipa", "Petugas Kebersihan", "Pengasuh anak", "Tukang Potong Rumput", "Guru Les Privat"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_search);

        search_bar = findViewById(R.id.search_bar);
        search_bar.clearFocus();
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });

        /*Recycle View For Employes Filter*/
        employes[0] = new Employe("Cleaning Service", "https://static.sehatq.com/content/review/thumb/1638698147.jpeg");
        employes[1] = new Employe("Tukang Kebun", "https://akcdn.detik.net.id/visual/2018/07/18/faddcf39-1872-4434-aa50-a3d8830d2a16_169.jpeg?w=650");
        employes[2] = new Employe("Servis Mesin Cuci", "https://kanalbisnis.com/wp-content/uploads/2022/05/Mesin-Cuci-Bandung-1.jpg");
        employes[3] = new Employe("Tukang Ledeng", "https://st2.depositphotos.com/1031551/6979/i/450/depositphotos_69797965-stock-photo-plumber-at-work.jpg");
        employes[4] = new Employe("Kuli", "https://img.okezone.com/content/2018/08/16/194/1937686/jangan-sampai-kalah-ini-kisah-kuli-kuli-genting-bertubuh-binaragawan-wuJZTbRsmw.jpg");
        employes[5] = new Employe("Les Privat", "https://www.administrator.funteacherprivate.com/storage/file_blog/1646211223lowongan%20guru%20les%20privat.jpg");
        employes[6] = new Employe("Instruktur Gym", "https://img.lovepik.com/photo/20211119/medium/lovepik-gym-instructors-exercise-guidance-picture_500307995.jpg");
        employes[7] = new Employe("Bodyguard", "https://i.pinimg.com/736x/9e/c4/24/9ec424ba3f6fd7c3f286b77f7b96e604.jpg");
        employes[8] = new Employe("Servis Elektronik", "https://cdn-2.tstatic.net/jambi/foto/bank/images/atik-teknisi-elektronik.jpg");

        final int columns = 3;
        RecyclerView rvEmploye = findViewById(R.id.rv_employe);
        rvEmploye.setLayoutManager(new GridLayoutManager(this, columns));
        EmployeAdapter employeAdapter = new EmployeAdapter(this, employes);
        rvEmploye.setAdapter(employeAdapter);


        Button btn_apply = findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Filter Telah Diterapkan", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    private void filterlist(String newText) {
        List<String> filterlist = new ArrayList<String>();
    }
}
