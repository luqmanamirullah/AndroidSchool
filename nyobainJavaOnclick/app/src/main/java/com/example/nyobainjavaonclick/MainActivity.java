package com.example.nyobainjavaonclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView pza = findViewById(R.id.imageView1);
        ImageView pza2 = findViewById(R.id.imageView2);
        ImageView next = findViewById(R.id.imageViewnext);
        ImageView back = findViewById(R.id.imageViewback);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.imageViewnext){
                    pza.setVisibility(View.GONE);
                    pza2.setVisibility(View.VISIBLE);
                } else {
                    pza.setVisibility(View.VISIBLE);
                    pza2.setVisibility(View.GONE);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.imageViewback){
                    pza.setVisibility(View.VISIBLE);
                    pza2.setVisibility(View.GONE);
                } else {
                    pza.setVisibility(View.GONE);
                    pza2.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}