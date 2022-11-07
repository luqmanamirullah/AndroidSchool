package com.example.belajarintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button explicitintent = findViewById(R.id.explicitintent);
        explicitintent.setOnClickListener(this);
        Button implicitintent = findViewById(R.id.implicitintent);
        implicitintent.setOnClickListener(this);
    }

     @Override
     public void onStart() {
         super.onStart();
         Toast.makeText(this, "Siklus Hidup onStart", Toast.LENGTH_LONG).show();
     }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this, "Siklus Hidup onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    public  void onPause(){
        super.onPause();
        Toast.makeText(this, "Siklus Hidup onPause", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStop(){
        super.onStop();
        Toast.makeText(this, "Siklus Hidup onStop", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Siklus Hidup onDestroy", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.explicitintent:
                Intent explicit = new Intent(MainActivity.this, intent.class);
                startActivity(explicit);
                break;
            case R.id.implicitintent:
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/training/snackbar"));
                startActivity(implicit);
                break;
            default:
                break;
        }
    }
}