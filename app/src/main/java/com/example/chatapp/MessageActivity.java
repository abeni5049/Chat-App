package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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


public class MessageActivity extends AppCompatActivity {

    RelativeLayout back,messageContainer;
    EditText messageBox;
    ImageView send,smile,attach,mic;
    MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String uid = LogInActivity.user.getUid();
        // Write a user to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(uid).child("message");

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.message_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<String> messageArray = new ArrayList<>();
        ArrayList<Boolean> senderArray = new ArrayList<>();
        adapter = new MessageAdapter(this,messageArray,senderArray);
        ListView listView = findViewById(R.id.message_list);
        listView.setAdapter(adapter);


        back = findViewById(R.id.messageBackground);
        back.setBackgroundColor(getResources().getColor(R.color.primary_light));
        messageContainer = findViewById(R.id.message_container);

        messageBox = findViewById(R.id.message_box);
        smile = findViewById(R.id.smile);
        attach = findViewById(R.id.attach);
        mic = findViewById(R.id.mic);
        send = findViewById(R.id.send);
        //messageContainerParams = (RelativeLayout.LayoutParams) messageBox.getLayoutParams();

        // Read from the database
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                Message value = snapshot.getValue(Message.class);
                assert value != null;
                senderArray.add(true);
//                String val = value.getSender();
//                Log.i("sender",val);
//                String val2= LogInActivity.user.getDisplayName();
//                Log.i("sender",val2);
//                if(LogInActivity.user.getDisplayName().equals(value.getSender())){
//                    senderArray.add(true);
//                    Log.i("sender","true");
//                }else {
//                    senderArray.add(false);
//                    Log.i("sender",value.getSender());
//                }
                messageArray.add(value.getText());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        }) ;


        messageBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(messageBox.getText().toString().trim().equals("")){
                    send.setVisibility(View.GONE);
                    attach.setVisibility(View.VISIBLE);
                    mic.setVisibility(View.VISIBLE);
                }else{
                    send.setVisibility(View.VISIBLE);
                    attach.setVisibility(View.GONE);
                    mic.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        send.setOnClickListener(v -> {
            String message = messageBox.getText().toString().trim();
            DatabaseReference ref = myRef.push();
            ref.child("text").setValue(message);
            ref.child("sender").setValue(LogInActivity.user.getDisplayName());
            messageBox.setText("");
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}