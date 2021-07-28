package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] userName ={
            "Title 1","Title 2",
            "Title 3","Title 4",
            "Title 5",
    };

    String[] subtitle ={
            "Sub Title 1","Sub Title 2",
            "Sub Title 3","Sub Title 4",
            "Sub Title 5",
    };

    String[] profile={
            "A","B","C","D","E"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewAdapter adapter = new ListViewAdapter(this,userName,subtitle,profile);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this,MessageActivity.class);
            startActivity(intent);
        });

    }

}
