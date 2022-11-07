package com.example.conary_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.clickToCompetition:
                Intent explicit = new Intent(MainActivity.this, CptActivity.class);
                startActivity(explicit);
                break;
            case R.id.clickToRank:
                Intent explicit2 = new Intent(MainActivity.this, RankActivity.class);
                startActivity(explicit2);
                break;
            case R.id.clickToProfile:
                Intent explicit3 = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(explicit3);
                break;
            case R.id.clickToLearn:
                Toast.makeText(this, "Anda Sudah Berada di Section Learn", Toast.LENGTH_LONG).show();
        }
    }
}