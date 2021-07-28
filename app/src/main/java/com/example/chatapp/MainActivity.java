package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> userName;
    ArrayList<String> subtitle;
    ArrayList<Character> profile;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = new ArrayList<>();
        subtitle = new ArrayList<>();
        profile = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = "1pUiLVhyzseX4Q3b9HwQd4P4NL12";
        String uid2 = "ovPshVxVx2OXcOa0mFJP5m6345v1";
        DatabaseReference myRef = database.getReference("users").child(uid).child("chats").child(uid2).child("username");
        DatabaseReference myRef2 = database.getReference("users").child(uid2).child("chats").child(uid).child("username");
        myRef.setValue("Iris");
        myRef2.setValue("Abeni");

        ListViewAdapter adapter = new ListViewAdapter(this,userName,subtitle,profile);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                userName.add(value);
                subtitle.add("hi");
                profile.add(value.charAt(0));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this,MessageActivity.class);
            startActivity(intent);
        });

    }

}
