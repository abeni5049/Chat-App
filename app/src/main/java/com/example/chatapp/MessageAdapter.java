package com.example.chatapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class MessageAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> message;
    private final ArrayList<Boolean> sender;

    public MessageAdapter(@NonNull Activity context, ArrayList<String> message ,ArrayList<Boolean> sender) {
        super(context, R.layout.message_box,message);
        this.context = context;
        this.message = message;
        this.sender  = sender;
    }

    public View getView(int position , View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.message_box,null,true);
        TextView messageTextView = rowView.findViewById(R.id.message);
        messageTextView.setText(message.get(position));
        RelativeLayout container = rowView.findViewById(R.id.message_container);
        RelativeLayout.LayoutParams containerLayoutParams = (RelativeLayout.LayoutParams) container.getLayoutParams();
        boolean isSender = sender.get(position);
        if(isSender) {
            containerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            GradientDrawable drawable = (GradientDrawable)container.getBackground();
            drawable.setColor(Color.BLUE); // set solid color
            messageTextView.setTextColor(Color.WHITE);
        }else{
            containerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            GradientDrawable drawable = (GradientDrawable)container.getBackground();
            drawable.setColor(Color.WHITE); // set solid color
            messageTextView.setTextColor(Color.BLACK );
        }
        return rowView;
    }
}
