package com.example.chatapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> userName;
    private final ArrayList<String> latestMessage;
    private final ArrayList<Character> profile;

    public ListViewAdapter(@NonNull Activity context, ArrayList<String> userName , ArrayList<String>latestMessage, ArrayList<Character> profile) {
        super(context, R.layout.listitem,userName);
        this.context = context;
        this.userName = userName;
        this.latestMessage = latestMessage;
        this.profile = profile;
    }

    public View getView(int position , View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listitem,null,true);

        TextView profilePicture = rowView.findViewById(R.id.profile_picture);
        TextView userNameTextView = rowView.findViewById(R.id.user_name);
        TextView latestMessageTextView = rowView.findViewById(R.id.latest_message);

        profilePicture.setText(profile.get(position).toString());
        userNameTextView.setText(userName.get(position));
        latestMessageTextView.setText(latestMessage.get(position));

        return rowView;
    }
}
