package com.example.chatmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] list_mapel = {"Math", "Science", "Physic", "English"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterarray;
    LinearLayout friends;
    Button btn_select;
    String result;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_select = (Button) findViewById(R.id.btn_select);
        friends = (LinearLayout) findViewById(R.id.friends);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_completeText);
        adapterarray = new ArrayAdapter<String>(this, R.layout.drop_list, list_mapel);
        autoCompleteTextView.setAdapter(adapterarray);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                result = item;
                Toast.makeText(getApplicationContext(), "You Selected: "+ item, Toast.LENGTH_LONG).show();
                friends.setVisibility(View.VISIBLE);
            }
        });



//        btn_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }
}