package com.example.intentlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button getbtn;
    EditText inputText;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getbtn = (Button) findViewById(R.id.getValue);
        inputText = (EditText)findViewById(R.id.inputText);
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(MainActivity.this, IntentActivity.class);
                name= inputText.getText().toString();
                nextPage.putExtra("value", name);
                startActivity(nextPage);
                finish();
            }
        });
    }
}