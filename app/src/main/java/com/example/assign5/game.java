/*
    Ethan McCrary
    CSCI 4010
    Assignment 5
 */
package com.example.assign5;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.flexbox.FlexboxLayout;

public class game extends AppCompatActivity
        implements View.OnClickListener {

    TextView tv;
    ImageButton[][] ibs;
    Boolean[][] xChecked;
    Boolean[][] oChecked;
    ImageButton oButton;
    ImageButton xButton;
    Button reset;
    int imageRes = 0;
    int turn;
    int id;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        FlexboxLayout flexboxLayout = findViewById(R.id.flexBot);
        flexboxLayout.setBackgroundColor(Color.DKGRAY);
        ibs = new ImageButton[6][6];
        xChecked = new Boolean[6][6];
        oChecked = new Boolean[6][6];
        id = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                xChecked[i][j] = false;
                oChecked[i][j] = false;
                ibs[i][j] = new ImageButton(this);
                ibs[i][j].setBackgroundResource(R.drawable.square);
                ibs[i][j].setOnClickListener(this);
                ibs[i][j].setId(id);
                flexboxLayout.addView(ibs[i][j]);
                id++;
            }
        }

        xButton = new ImageButton(this);
        oButton = new ImageButton(this);
        xButton.setImageResource(R.drawable.x);
        oButton.setImageResource(R.drawable.o);
        reset = new Button(this);
        reset.setOnClickListener(this);
        reset.setText("Reset");
        xButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageRes = R.drawable.x;
            }
        });
        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageRes = R.drawable.o;
            }
        });
        tv = new TextView(this);
        tv.setId(View.generateViewId());
        tv.setText("The Current Player's Turn is: ORDER");
        flexboxLayout.addView(oButton);
        flexboxLayout.addView(xButton);
        flexboxLayout.addView(reset);
        flexboxLayout.addView(tv);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == reset.getId()) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    tv.setText("The Current Player's Turn is: ORDER");
                    ibs[i][j].setOnClickListener(this);
                    ibs[i][j].setImageResource(0);
                    xChecked[i][j] = false;
                    oChecked[i][j] = false;
                }
            }
            turn = 0;
            imageRes = 0;
        }

       if (imageRes != 0) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (view.getId() == ibs[i][j].getId()) {
                        ibs[i][j].setImageResource(imageRes);
                        if (imageRes == R.drawable.x) {
                            xChecked[i][j] = true;
                        } else {
                            oChecked[i][j] = true;
                        }
                        ibs[i][j].setOnClickListener(null);
                        if (turn % 2 == 0) {
                            ibs[i][j].setColorFilter(Color.CYAN);
                            tv.setText("The Current Player's Turn is: CHAOS");
                        } else {
                            ibs[i][j].setColorFilter(Color.RED);
                            tv.setText("The Current Player's Turn is: ORDER");
                        }

                        turn++;
                        imageRes = 0;
                    }
                }
            }
        }
        if (checkWin()) {
            tv.setText("Order has Won!");
        } else if (turn == 36) {
            tv.setText("Chaos has Won!");
        }
    }

    public Boolean checkWin() {
        Boolean value = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (
                    //HORIZONTAL LEFT X
                    (xChecked[i][0] && xChecked[i][1] && xChecked[i][2] &&
                        xChecked[i][3] && xChecked[i][4]) ||
                    //HORIZONTAL RIGHT X
                    (xChecked[i][1] && xChecked[i][2] && xChecked[i][3] &&
                        xChecked[i][4] && xChecked[i][5]) ||
                    //HORIZONTAL LEFT O
                    (oChecked[i][0] && oChecked[i][1] && oChecked[i][2] &&
                        oChecked[i][3] && oChecked[i][4]) ||
                    //HORIZONTAL RIGHT O
                    (oChecked[i][1] && oChecked[i][2] && oChecked[i][3] &&
                        oChecked[i][4] && oChecked[i][5]) ||
                    //VERTICAL TOP X
                    (xChecked[0][i] && xChecked[1][i] && xChecked[2][i] &&
                        xChecked[3][i] && xChecked[4][i]) ||
                    //VERTICAL BOTTOM X
                    (xChecked[1][i] && xChecked[2][i] && xChecked[3][i] &&
                        xChecked[4][i] && xChecked[5][i]) ||
                    //VERTICAL TOP O
                    (oChecked[0][i] && oChecked[1][i] && oChecked[2][i] &&
                        oChecked[3][i] && oChecked[4][i]) ||
                    //VERTICAL BOTTOM O
                    (oChecked[1][i] && oChecked[2][i] && oChecked[3][i] &&
                        oChecked[4][i] && oChecked[5][i]) ||
                    //MIDDLE DIAGONAL LEFT X
                    (xChecked[0][0] && xChecked[1][1] && xChecked[2][2] &&
                        xChecked[3][3] && xChecked[4][4]) ||
                    //MIDDLE DIAGONAL RIGHT X
                    (xChecked[1][1] && xChecked[2][2] && xChecked[3][3] &&
                        xChecked[4][4] && xChecked[5][5]) ||
                    //MIDDLE DIAGONAL LEFT O
                    (oChecked[0][0] && oChecked[1][1] && oChecked[2][2] &&
                        oChecked[3][3] && oChecked[4][4]) ||
                    //MIDDLE DIAGONAL RIGHT O
                    (oChecked[1][1] && oChecked[2][2] && oChecked[3][3] &&
                        oChecked[4][4] && oChecked[5][5]) ||
                    //BOTTOM DIAGONAL LEFT X
                    (xChecked[5][0] && xChecked[4][1] && xChecked[3][2] &&
                        xChecked[2][3] && xChecked[1][4]) ||
                    //BOTTOM DIAGONAL RIGHT X
                    (xChecked[4][1] && xChecked[3][2] && xChecked[2][3] &&
                        xChecked[1][4] && xChecked[0][5]) ||
                    //BOTTOM DIAGONAL LEFT O
                    (oChecked[5][0] && oChecked[4][1] && oChecked[3][2] &&
                        oChecked[2][3] && oChecked[1][4]) ||
                    //BOTTOM DIAGONAL RIGHT O
                    (oChecked[4][1] && oChecked[3][2] && oChecked[2][3] &&
                        oChecked[1][4] && oChecked[0][5]) ||
                    //TOP LEFT DIAGONAL X
                    (xChecked[0][1] && xChecked[1][2] && xChecked[2][3] &&
                        xChecked[3][4] && xChecked[4][5]) ||
                    //TOP LEFT DIAGONAL O
                    (oChecked[0][1] && oChecked[1][2] && oChecked[2][3] &&
                        oChecked[3][4] && oChecked[4][5]) ||
                    //BOTTOM LEFT DIAGONAL X
                    (xChecked[1][0] && xChecked[2][1] && xChecked[3][2] &&
                        xChecked[4][3] && xChecked[5][4]) ||
                    //BOTTOM LEFT DIAGONAL O
                    (oChecked[1][0] && oChecked[2][1] && oChecked[3][2] &&
                        oChecked[4][3] && oChecked[5][4]) ||
                    //TOP RIGHT DIAGONAL X
                    (xChecked[0][4] && xChecked[1][3] && xChecked[2][2] &&
                        xChecked[3][1] && xChecked[4][0]) ||
                    //TOP RIGHT DIAGONAL O
                    (oChecked[0][4] && oChecked[1][3] && oChecked[2][2] &&
                        oChecked[3][1] && oChecked[4][0]) ||
                    //BOTTOM RIGHT DIAGONAL X
                    (xChecked[1][5] && xChecked[2][4] && xChecked[3][3] &&
                        xChecked[4][2] && xChecked[5][1]) ||
                    //BOTTOM RIGHT DIAGONAL O
                    (oChecked[1][5] && oChecked[2][4] && oChecked[3][3] &&
                        oChecked[4][2] && oChecked[5][1])
                    )
                {
                    value = true;
                    for (int k = 0; k < 6; k++) {
                        for (int l = 0; l < 6; l++) {
                            ibs[k][l].setOnClickListener(null);
                        }
                    }
                }
            }
        }
        return value;
    }
}
