package com.example.quizsederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton ans_1b = findViewById(R.id.ans_1b);
        RadioButton ans_2a = findViewById(R.id.ans_2a);
        RadioButton ans_3b = findViewById(R.id.ans_3b);
        RadioButton ans_4c = findViewById(R.id.ans_4c);
        RadioButton ans_5c = findViewById(R.id.ans_5c);
        RadioButton ans_6b = findViewById(R.id.ans_6b);
        RadioButton ans_7b = findViewById(R.id.ans_7b);
        RadioButton ans_8c = findViewById(R.id.ans_8c);
        RadioButton ans_9b = findViewById(R.id.ans_9b);
        RadioButton ans_10b = findViewById(R.id.ans_10b);

        FrameLayout frame_result = findViewById(R.id.frame_result);
        TextView value = findViewById(R.id.nilai);

        RadioButton[] data_ans = {ans_1b, ans_2a,ans_3b, ans_4c, ans_5c, ans_6b, ans_7b, ans_8c, ans_9b, ans_10b};

        Button sumbit_ans = findViewById(R.id.submit);
        sumbit_ans.setOnClickListener(new View.OnClickListener() {
            int skor = 0;
            @Override
            public void onClick(View view) {
                frame_result.setVisibility(frame_result.VISIBLE);
               for (int i = 0; i < 10; i++){
                   if(data_ans[i].isChecked()){
                       skor += 10;
                   }
               }
              value.setText("Nilai: " + skor);

            }
        });
    }
}