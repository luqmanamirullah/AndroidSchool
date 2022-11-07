package com.example.asframelayout1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView p, p2, n, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        p = findViewById(R.id.imageView1);
        p2 = findViewById(R.id.imageView2);
        n = findViewById(R.id.imageViewnext);
        b = findViewById(R.id.imageViewback);
        p.setOnClickListener(this);
        p2.setOnClickListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageViewnext){
            p.setVisibility(View.GONE);
            p2.setVisibility(View.VISIBLE);
        } else {
            p2.setVisibility(View.GONE);
            p.setVisibility(View.VISIBLE);
        }
    }
}