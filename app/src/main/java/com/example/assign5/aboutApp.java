/*
    Ethan McCrary
    CSCI 4010
    Assignment 5
 */
package com.example.assign5;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class aboutApp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app);

        TextView tv = findViewById(R.id.textView3);
        String details =
                "This is an app that allows the user to play the Tic-Tac-Toe spinoff, " +
                "Order and Chaos. " +
                "This app was written with Java by Ethan McCrary " +
                "For Mobile Dev. I.";
        tv.setText(details);
    }
}
