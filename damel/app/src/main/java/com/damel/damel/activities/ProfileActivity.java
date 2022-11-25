package com.damel.damel.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.damel.damel.R;
import com.damel.damel.adapters.SettingAdapter;
import com.damel.damel.models.Setting;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private final int MAX_SETTING = 3;
    private Setting[] settings = new Setting[MAX_SETTING];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_profile);

        settings[0] = new Setting("Alamat", "Alamat rumah, nomor telepon", R.drawable.ic_address);
        settings[1] = new Setting("Pembayaran", "Metode pembayaran, rekening bank", R.drawable.ic_payment);
        settings[2] = new Setting("Bantuan", "Laporkan, layanan konsumen", R.drawable.ic_help);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            TextView tvUsername = findViewById(R.id.username);
            TextView tvEmail = findViewById(R.id.email);
            ImageView profileImage = findViewById(R.id.profile_image);
            tvUsername.setText(user.getDisplayName());
            tvEmail.setText(user.getEmail());
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .into(profileImage);
            } else {
                profileImage.setImageResource(R.drawable.user_placeholder);
            }
        }

        ConstraintLayout btnLogout = findViewById(R.id.logout);
        btnLogout.setOnClickListener(v -> {
            showLogoutConfirmDialog();
        });

        RecyclerView rvSetting = findViewById(R.id.rv_settings);
        rvSetting.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSetting.setAdapter(new SettingAdapter(this, settings));


    }

    public void showLogoutConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setMessage("Yakin ingin keluar dari akun?");
        builder.setCancelable(false);
        builder.setPositiveButton("Keluar", (dialog, which) -> {
            mAuth.signOut();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show();
            finishAffinity();
        });
        builder.setNegativeButton("Batal", (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}