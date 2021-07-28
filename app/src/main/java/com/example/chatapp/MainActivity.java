package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> userName;
    ArrayList<String> subtitle;
    ArrayList<String> uid;
    ArrayList<Character> profile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = new ArrayList<>();
        subtitle = new ArrayList<>();
        profile = new ArrayList<>();
        uid = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(LogInActivity.user.getUid()).child("chats");
        ListViewAdapter adapter = new ListViewAdapter(this,userName,subtitle,profile);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String username = child.child("username").getValue().toString();
                    String lastMessage="";
                    for( DataSnapshot snap  :child.child("messages").getChildren() ){
                        lastMessage = Objects.requireNonNull(snap.child("text").getValue()).toString();
                    }
                    userName.add(username);
                    subtitle.add(lastMessage);
                    profile.add(username.charAt(0));
                    uid.add(child.getKey());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this,MessageActivity.class);
            intent.putExtra("uid",uid.get(position));
            startActivity(intent);
        });

    }

}
