package com.example.uhp2_inputuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText value1, value2, valueResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value1 = (EditText) findViewById(R.id.value1);
        value2 = (EditText) findViewById(R.id.value2);
        valueResult = (EditText) findViewById(R.id.valueResult);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!value1.getText().toString().equals("") && !value2.getText().toString().equals("")) {
                    int intvalue1 = Integer.parseInt(value1.getText().toString());
                    int intvalue2 = Integer.parseInt(value2.getText().toString());
                    valueResult.setText(String.valueOf(intvalue1 + intvalue2));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        value1.addTextChangedListener(textWatcher);
        value2.addTextChangedListener(textWatcher);
    }
}