package com.example.intentlayout;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity {
    TextView showName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity);
        showName = findViewById(R.id.name);
        name = getIntent().getExtras().getString("value");
        showName.setText(name);
    }
}
