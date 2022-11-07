package com.example.luqmanamirlullah_tugasfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_forget = findViewById(R.id.btn_forgetpswrd);
        btn_forget.setOnClickListener(this);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_forgetpswrd:
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout_alert, new FragmentAlert()).commit();
                break;
            case R.id.btn_login:
                Intent explicit =new Intent(MainActivity.this, HompeActivity.class);
                startActivity(explicit);
                break;
        }
    }
}