/*
    Ethan McCrary
    CSCI 4010
    Assignment 5
 */
package com.example.assign5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.aboutApp).setOnClickListener(this);
        findViewById(R.id.aboutGame).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.play) {
            Intent intent = new Intent(getApplicationContext(), game.class);
            startActivity(intent);
        } else if (v.getId() == R.id.aboutGame) {
            openWebPage("https://en.wikipedia.org/wiki/Order_and_Chaos");
        } else if (v.getId() == R.id.aboutApp) {
            Intent intent = new Intent(getApplicationContext(), aboutApp.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "ERROR: Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void openWebPage(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
