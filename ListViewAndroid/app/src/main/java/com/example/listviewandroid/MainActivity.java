    package com.example.listviewandroid;

    import androidx.appcompat.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        ListView listView;
        ArrayList<String> list = new ArrayList<String >();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            listView = (ListView) findViewById(R.id.listview);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(arrayAdapter);
            list.add("RPL");
            list.add("TKJ");
            list.add("MM");
            list.add("AVI");
            list.add("TOI");
        }
    }