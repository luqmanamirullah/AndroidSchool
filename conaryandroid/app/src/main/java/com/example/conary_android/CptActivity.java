package com.example.conary_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CptActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        ImageButton toLearn = findViewById(R.id.clickToLearn);
        toLearn.setOnClickListener(this);
        ImageButton toCompetition = findViewById(R.id.clickToCompetition);
        toCompetition.setOnClickListener(this);
        ImageButton toRank = findViewById(R.id.clickToRank);
        toRank.setOnClickListener(this);
        ImageButton toProfile = findViewById(R.id.clickToProfile);
        toProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clickToLearn:
                Intent explicit = new Intent(CptActivity.this, MainActivity.class);
                startActivity(explicit);
                break;
            case R.id.clickToRank:
                Intent explicit2 = new Intent(CptActivity.this, RankActivity.class);
                startActivity(explicit2);
                break;
            case R.id.clickToProfile:
                Intent explicit3 = new Intent(CptActivity.this, ProfileActivity.class);
                startActivity(explicit3);
                break;
            case R.id.clickToCompetition:
                Toast.makeText(this, "Anda Sudah Berada di Section Competition", Toast.LENGTH_LONG).show();
        }
    }
}
