/* ************************************************************************************************\
*                                                                                                  *
*          Name: Justin De Guzman                                                                  *
*   CS Username: deguzman                                                                          *
*       Project: Tic Tac Nole                                                                      *
*       Created: 27 June 2018                                                                      *
* Last modified:  5 July 2018                                                                      *
*                                                                                                  *
* Description:                                                                                     *
*  For this project, various if-else and switch statements were used for the execution of control. *
*  The AI is a simple AI which only takes player selection into account; e.g. it does not know if  *
*  it has two of its own fruits in a row to create a third.                                        *
*  Button art was created by a friend, Mindy Goto.                                                 *
*                                                                                                  *
*  (Update - 5 July 2018) The AI has been upgraded! Upon entering checkWorld(), the AI first       *
*  checks to see if it has two fruits in a row to create a tic tac toe; if not, it proceeds to     *
*  check if the player has two fruits that can be blocked, otherwise it will pick a random         *
*  available square. 100% of the code in this project, including AI, was written by myself.        *
*                                                                                                  *
*  Features not implemented:                                                                       *
*  - An option to make the AI pick a square first.                                                 *
*  - A landscape orientation.                                                                      *
*                                                                                                  *
* Credits: Button art (egg.png, eggcut.png, grape.png, grapecut.png) by Mindy Goto.                *
*                                                                                                  *
* Disclaimer: If eventual robot takeover does occur, I am not to be held responsible.              *
*                                                                                                  *
* P.S. blame Boston Dynamics: https://media.giphy.com/media/xT9DPp7lYtKlM0QzII/giphy.gif                                                *
*                                                                                                  *
\************************************************************************************************ */

package com.example.jdgzm.cop4656_tictacnole;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Function initializations:
    ImageButton top1;
    ImageButton top2;
    ImageButton top3;
    ImageButton mid1;
    ImageButton mid2;
    ImageButton mid3;
    ImageButton bot1;
    ImageButton bot2;
    ImageButton bot3;

    ImageView adBtn;

    Button pveButton;
    Button pvpButton;

    TextView announce;

    int who, p1total = 0, p2total = 0, winner;
    Boolean t1m, t2m, t3m, m1m, m2m, m3m, b1m, b2m, b3m;
    Boolean diag1, diag2, col1, col2, col3, row1, row2, row3;
    int t11, t22, t33, m11, m22, m33, b11, b22, b33;

    Boolean win;
    Boolean ROBOT;
    Random r = new Random();
    int randomized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        win = false;
        ROBOT = false;

        // onCreate inits:
        top1 = findViewById(R.id.t1);
        top1.setOnClickListener(this);

        top2 = findViewById(R.id.t2);
        top2.setOnClickListener(this);

        top3 = findViewById(R.id.t3);
        top3.setOnClickListener(this);

        mid1 = findViewById(R.id.m1);
        mid1.setOnClickListener(this);

        mid2 = findViewById(R.id.m2);
        mid2.setOnClickListener(this);

        mid3 = findViewById(R.id.m3);
        mid3.setOnClickListener(this);

        bot1 = findViewById(R.id.b1);
        bot1.setOnClickListener(this);

        bot2 = findViewById(R.id.b2);
        bot2.setOnClickListener(this);

        bot3 = findViewById(R.id.b3);
        bot3.setOnClickListener(this);

        t1m = false; t2m = false; t3m = false;
        m1m = false; m2m = false; m3m = false;
        b1m = false; b2m = false; b3m = false;
        who = 0;

        diag1 = false; diag2 = false;
        col1 = false; col2 = false; col3 = false;
        row1 = false; row2 = false; row3 = false;

        t11 = 0; t22 = 0; t33 = 0;
        m11 = 0; m22 = 0; m33 = 0;
        b11 = 0; b22 = 0; b33 = 0;

        pveButton = findViewById(R.id.pveButton);
        pvpButton = findViewById(R.id.pvpButton);

        // This portion keeps track of the scoring system
        p1total = 0; p2total = 0;
        winner = 0;
        announce = findViewById(R.id.announcement);

        adBtn = findViewById(R.id.advertisement);

        // button listeners
        // reset1
        pveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                win = false;
                ROBOT = true;

                adBtn.setVisibility(View.GONE);

                t1m = false; t2m = false; t3m = false;
                m1m = false; m2m = false; m3m = false;
                b1m = false; b2m = false; b3m = false;

                randomized = r.nextInt(9 - 0);

                    who = 0;
                    announce.setText("You go first!");

                top1.setImageResource(R.drawable.blank); top2.setImageResource(R.drawable.blank); top3.setImageResource(R.drawable.blank);
                mid1.setImageResource(R.drawable.blank); mid2.setImageResource(R.drawable.blank); mid3.setImageResource(R.drawable.blank);
                bot1.setImageResource(R.drawable.blank); bot2.setImageResource(R.drawable.blank); bot3.setImageResource(R.drawable.blank);

                diag1 = false; diag2 = false;
                col1 = false; col2 = false; col3 = false;
                row1 = false; row2 = false; row3 = false;

                t11 = 0; t22 = 0; t33 = 0;
                m11 = 0; m22 = 0; m33 = 0;
                b11 = 0; b22 = 0; b33 = 0;
            }
        });

        // reset2
        pvpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                win = false;
                ROBOT = false;
                who = 0;

                adBtn.setVisibility(View.GONE);

                t1m = false; t2m = false; t3m = false;
                m1m = false; m2m = false; m3m = false;
                b1m = false; b2m = false; b3m = false;

                top1.setImageResource(R.drawable.blank); top2.setImageResource(R.drawable.blank); top3.setImageResource(R.drawable.blank);
                mid1.setImageResource(R.drawable.blank); mid2.setImageResource(R.drawable.blank); mid3.setImageResource(R.drawable.blank);
                bot1.setImageResource(R.drawable.blank); bot2.setImageResource(R.drawable.blank); bot3.setImageResource(R.drawable.blank);

                diag1 = false; diag2 = false;
                col1 = false; col2 = false; col3 = false;
                row1 = false; row2 = false; row3 = false;

                t11 = 0; t22 = 0; t33 = 0;
                m11 = 0; m22 = 0; m33 = 0;
                b11 = 0; b22 = 0; b33 = 0;

                announce.setText("Select your squares!");
            }
        });

    } // end onCreate

    @Override
    public void onClick(View v)
    {
        if (!win && !ROBOT)
        {
            switch (v.getId())
            {
                case R.id.t1:
                    if (who == 0 && !t1m)
                    {
                        top1.setImageResource(R.drawable.egg);
                        t11 = 1;
                        who = 1;
                        t1m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !t1m)
                    {
                        top1.setImageResource(R.drawable.grape);
                        t11 = 2;
                        who = 0;
                        t1m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.t2:
                    if (who == 0 && !t2m)
                    {
                        top2.setImageResource(R.drawable.egg);
                        t22 = 1;
                        who = 1;
                        t2m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !t2m)
                    {
                        top2.setImageResource(R.drawable.grape);
                        t22 = 2;
                        who = 0;
                        t2m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.t3:
                    if (who == 0 && !t3m)
                    {
                        top3.setImageResource(R.drawable.egg);
                        t33 = 1;
                        who = 1;
                        t3m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !t3m)
                    {
                        top3.setImageResource(R.drawable.grape);
                        t33 = 2;
                        who = 0;
                        t3m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.m1:
                    if (who == 0 && !m1m)
                    {
                        mid1.setImageResource(R.drawable.egg);
                        m11 = 1;
                        who = 1;
                        m1m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !m1m)
                    {
                        mid1.setImageResource(R.drawable.grape);
                        m11 = 2;
                        who = 0;
                        m1m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.m2:
                    if (who == 0 && !m2m)
                    {
                        mid2.setImageResource(R.drawable.egg);
                        m22 = 1;
                        who = 1;
                        m2m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !m2m)
                    {
                        mid2.setImageResource(R.drawable.grape);
                        m22 = 2;
                        who = 0;
                        m2m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.m3:
                    if (who == 0 && !m3m)
                    {
                        mid3.setImageResource(R.drawable.egg);
                        m33 = 1;
                        who = 1;
                        m3m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !m3m)
                    {
                        mid3.setImageResource(R.drawable.grape);
                        m33 = 2;
                        who = 0;
                        m3m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.b1:
                    if (who == 0 && !b1m)
                    {
                        bot1.setImageResource(R.drawable.egg);
                        b11 = 1;
                        who = 1;
                        b1m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !b1m)
                    {
                        bot1.setImageResource(R.drawable.grape);
                        b11 = 2;
                        who = 0;
                        b1m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.b2:
                    if (who == 0 && !b2m)
                    {
                        bot2.setImageResource(R.drawable.egg);
                        b22 = 1;
                        who = 1;
                        b2m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !b2m)
                    {
                        bot2.setImageResource(R.drawable.grape);
                        b22 = 2;
                        who = 0;
                        b2m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.b3:
                    if (who == 0 && !b3m)
                    {
                        bot3.setImageResource(R.drawable.egg);
                        b33 = 1;
                        who = 1;
                        b3m = true;
                        checkAll(0);
                    }
                    else if (who == 1 && !b3m)
                    {
                        bot3.setImageResource(R.drawable.grape);
                        b33 = 2;
                        who = 0;
                        b3m = true;
                        checkAll(1);
                    }
                    break;
            }
        }
        else if (!win)
        { // THIS IS WHEN THE PLAYER WANTS TO NEVER WIN (NOT REALLY)
            switch (v.getId())
            {
                case R.id.t1:
                    if (who == 0 && !t1m)
                    {
                        top1.setImageResource(R.drawable.egg);
                        t11 = 1;
                        who = 1;
                        t1m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !t1m)
                    {
                        top1.setImageResource(R.drawable.grape);
                        t11 = 2;
                        who = 0;
                        t1m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.t2:
                    if (who == 0 && !t2m)
                    {
                        top2.setImageResource(R.drawable.egg);
                        t22 = 1;
                        who = 1;
                        t2m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !t2m)
                    {
                        top2.setImageResource(R.drawable.grape);
                        t22 = 2;
                        who = 0;
                        t2m = true;
                        checkAll(1);
                    }
                        break;
                case R.id.t3:
                    if (who == 0 && !t3m)
                    {
                        top3.setImageResource(R.drawable.egg);
                        t33 = 1;
                        who = 1;
                        t3m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !t3m)
                    {
                        top3.setImageResource(R.drawable.grape);
                        t33 = 2;
                        who = 0;
                        t3m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.m1:
                    if (who == 0 && !m1m) {
                        mid1.setImageResource(R.drawable.egg);
                        m11 = 1;
                        who = 1;
                        m1m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !m1m)
                    {
                        mid1.setImageResource(R.drawable.grape);
                        m11 = 2;
                        who = 0;
                        m1m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.m2:
                    if (who == 0 && !m2m)
                    {
                        mid2.setImageResource(R.drawable.egg);
                        m22 = 1;
                        who = 1;
                        m2m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !m2m)
                    {
                        mid2.setImageResource(R.drawable.grape);
                        m22 = 2;
                        who = 0;
                        m2m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.m3:
                    if (who == 0 && !m3m)
                    {
                        mid3.setImageResource(R.drawable.egg);
                        m33 = 1;
                        who = 1;
                        m3m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !m3m)
                    {
                        mid3.setImageResource(R.drawable.grape);
                        m33 = 2;
                        who = 0;
                        m3m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.b1:
                    if (who == 0 && !b1m)
                    {
                        bot1.setImageResource(R.drawable.egg);
                        b11 = 1;
                        who = 1;
                        b1m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !b1m)
                    {
                        bot1.setImageResource(R.drawable.grape);
                        b11 = 2;
                        who = 0;
                        b1m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.b2:
                    if (who == 0 && !b2m)
                    {
                        bot2.setImageResource(R.drawable.egg);
                        b22 = 1;
                        who = 1;
                        b2m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !b2m)
                    {
                        bot2.setImageResource(R.drawable.grape);
                        b22 = 2;
                        who = 0;
                        b2m = true;
                        checkAll(1);
                    }
                    break;
                case R.id.b3:
                    if (who == 0 && !b3m)
                    {
                        bot3.setImageResource(R.drawable.egg);
                        b33 = 1;
                        who = 1;
                        b3m = true;
                        checkAll(0);
                        if (!win)
                            commenceRobotTakeover();
                    }
                    else if (who == 1 && !b3m)
                    {
                        bot3.setImageResource(R.drawable.grape);
                        b33 = 2;
                        who = 0;
                        b3m = true;
                        checkAll(1);
                    }
                    break;
            }
        }
    } // end onClick switch for 9 boxes

    /*
    top1, top2, top3
    mid1, mid2, mid3
    bot1, bot2, bot3
    */

    public void checkAll (int who)
    {
        // diagonal 1
        if (t11 == 1 && m22 == 1 && b33 == 1)
        {
            diag1 = true;
            highlight(0, 0);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (t11 == 2 && m22 == 2 && b33 == 2)
        {
            diag1 = true;
            highlight(1, 0);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        //diagonal 2
        else if (t33 == 1 && m22 == 1 && b11 == 1)
        {
            diag2 = true;
            highlight(0, 1);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (t33 == 2 && m22 == 2 && b11 == 2)
        {
            diag2 = true;
            highlight(1, 1);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        // row 1
        else if (t11 == 1 && t22 == 1 && t33 == 1)
        {
            row1 = true;
            highlight(0, 2);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (t11 == 2 && t22 == 2 && t33 == 2)
        {
            row1 = true;
            highlight(1, 2);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        // row 2
        else if (m11 == 1 && m22 == 1 && m33 == 1)
        {
            row1 = true;
            highlight(0, 3);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (m11 == 2 && m22 == 2 && m33 == 2)
        {
            row1 = true;
            highlight(1, 3);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        // row 3
        else if (b11 == 1 && b22 == 1 && b33 == 1)
        {
            row1 = true;
            highlight(0, 4);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (b11 == 2 && b22 == 2 && b33 == 2)
        {
            row1 = true;
            highlight(1, 4);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        // column 1
        else if (t11 == 1 && m11 == 1 && b11 == 1)
        {
            row1 = true;
            highlight(0, 5);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (t11 == 2 && m11 == 2 && b11 == 2)
        {
            row1 = true;
            highlight(1, 5);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        // column 2
        else if (t22 == 1 && m22 == 1 && b22 == 1)
        {
            row1 = true;
            highlight(0, 6);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (t22 == 2 && m22 == 2 && b22 == 2)
        {
            row1 = true;
            highlight(1, 6);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }

        // column 3
        else if (t33 == 1 && m33 == 1 && b33 == 1)
        {
            row1 = true;
            highlight(0, 7);
            announce.setText("Player 1 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
        else if (t33 == 2 && m33 == 2 && b33 == 2)
        {
            row1 = true;
            highlight(1, 7);
            announce.setText("Player 2 wins! Select a button to play again! \u2192");
            adBtn.setVisibility(View.VISIBLE);
        }
    } // end checkAll()

    public void highlight (int winner, int how)
    {
        // how values:
        // 0 = diag1, 1 = diag2
        // 2 = row1, 3 = row2, 4 = row3
        // 5 = col1, 6 = col2, 7 = col3

        switch (how)
        {
            case 0:
                if (winner == 0)
                {
                    top1.setImageResource(R.drawable.eggcut);
                    mid2.setImageResource(R.drawable.eggcut);
                    bot3.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    top1.setImageResource(R.drawable.grapecut);
                    mid2.setImageResource(R.drawable.grapecut);
                    bot3.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 1:
                if (winner == 0)
                {
                    top3.setImageResource(R.drawable.eggcut);
                    mid2.setImageResource(R.drawable.eggcut);
                    bot1.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    top3.setImageResource(R.drawable.grapecut);
                    mid2.setImageResource(R.drawable.grapecut);
                    bot1.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 2:
                if (winner == 0)
                {
                    top1.setImageResource(R.drawable.eggcut);
                    top2.setImageResource(R.drawable.eggcut);
                    top3.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    top1.setImageResource(R.drawable.grapecut);
                    top2.setImageResource(R.drawable.grapecut);
                    top3.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 3:
                if (winner == 0)
                {
                    mid1.setImageResource(R.drawable.eggcut);
                    mid2.setImageResource(R.drawable.eggcut);
                    mid3.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    mid1.setImageResource(R.drawable.grapecut);
                    mid2.setImageResource(R.drawable.grapecut);
                    mid3.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 4:
                if (winner == 0)
                {
                    bot1.setImageResource(R.drawable.eggcut);
                    bot2.setImageResource(R.drawable.eggcut);
                    bot3.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    bot1.setImageResource(R.drawable.grapecut);
                    bot2.setImageResource(R.drawable.grapecut);
                    bot3.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 5:
                if (winner == 0)
                {
                    top1.setImageResource(R.drawable.eggcut);
                    mid1.setImageResource(R.drawable.eggcut);
                    bot1.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    top1.setImageResource(R.drawable.grapecut);
                    mid1.setImageResource(R.drawable.grapecut);
                    bot1.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 6:
                if (winner == 0)
                {
                    top2.setImageResource(R.drawable.eggcut);
                    mid2.setImageResource(R.drawable.eggcut);
                    bot2.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    top2.setImageResource(R.drawable.grapecut);
                    mid2.setImageResource(R.drawable.grapecut);
                    bot2.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
            case 7:
                if (winner == 0)
                {
                    top3.setImageResource(R.drawable.eggcut);
                    mid3.setImageResource(R.drawable.eggcut);
                    bot3.setImageResource(R.drawable.eggcut);
                    win = true;
                }
                else if (winner == 1)
                {
                    top3.setImageResource(R.drawable.grapecut);
                    mid3.setImageResource(R.drawable.grapecut);
                    bot3.setImageResource(R.drawable.grapecut);
                    win = true;
                }
                break;
        }
    } // end highlight()

    public int checkWorld()
    {
        int selection;

        // 0 1 2
        // 3 4 5
        // 6 7 8

        // t11 t22 t33
        // m11 m22 m33
        // b11 b22 b33

        if (((t22 == 2 && t33 == 2) || (m11 == 2 && b11 == 2) || (m22 == 2 && b33 == 2)) && t11 == 0)
        {
            top1.setImageResource(R.drawable.egg);
            return 0;
        }
        else if (((t11 == 2 && t33 == 2) || (m22 == 2 && b22 == 2)) && t22 == 0)
        {
            top2.setImageResource(R.drawable.egg);
            return 1;
        }
        else if (((t11 == 2 && t22 == 2) || (m33 == 2 && b33 == 2) || (m22 == 2 && b11 == 2)) && t33 == 0)
        {
            top3.setImageResource(R.drawable.egg);
            return 2;
        }
        else if (((m22 == 2 && m33 == 2) || (t11 == 2 && b11 == 2)) && m11 == 0)
        {
            mid1.setImageResource(R.drawable.egg);
            return 3;
        }
        else if (((m11 == 2 && m33 == 2) || (t22 == 2 && b22 == 2) || (t11 == 2 && b33 == 2) || (b11 == 2 && t33 == 2)) && m22 == 0)
        {
            mid2.setImageResource(R.drawable.egg);
            return 4;
        }
        else if (((m11 == 2 && m22 == 2) || (t33 == 2 && b33 == 2)) && m33 == 0)
        {
            mid3.setImageResource(R.drawable.egg);
            return 5;
        }
        else if (((b22 == 2 && b33 == 2) || (t11 == 2 && m11 == 2) || (m22 == 2 && t33 == 2)) && b11 == 0)
        {
            bot1.setImageResource(R.drawable.egg);
            return 6;
        }
        else if (((b11 == 2 && b33 == 2) || (t22 == 2 && m22 == 2)) && b22 == 0)
        {
            bot2.setImageResource(R.drawable.egg);
            return 7;
        }
        else if (((b11 == 2 && b22 == 2) || (t11 == 2 && m22 == 2) || (t33 == 2 && m33 == 2)) && b33 == 0)
        {
            bot3.setImageResource(R.drawable.egg);
            return 8;
        }
        // This is the middle of this giant if-else statement! The above portion will make the AI check if it's got
        // two of its own fruits and an empty fruit to create a tic tac toe. The stuff below will make the AI check
        // if the player has two fruits that it can block.
        else if (((t22 == 1 && t33 == 1) || (m11 == 1 && b11 == 1) || (m22 == 1 && b33 == 1)) && t11 == 0)
        {
            top1.setImageResource(R.drawable.egg);
            return 0;
        }
        else if (((t11 == 1 && t33 == 1) || (m22 == 1 && b22 == 1)) && t22 == 0)
        {
            top2.setImageResource(R.drawable.egg);
            return 1;
        }
        else if (((t11 == 1 && t22 == 1) || (m33 == 1 && b33 == 1) || (m22 == 1 && b11 == 1)) && t33 == 0)
        {
            top3.setImageResource(R.drawable.egg);
            return 2;
        }
        else if (((m22 == 1 && m33 == 1) || (t11 == 1 && b11 == 1)) && m11 == 0)
        {
            mid1.setImageResource(R.drawable.egg);
            return 3;
        }
        else if (((m11 == 1 && m33 == 1) || (t22 == 1 && b22 == 1) || (t11 == 1 && b33 == 1) || (b11 == 1 && t33 == 1)) && m22 == 0)
        {
            mid2.setImageResource(R.drawable.egg);
            return 4;
        }
        else if (((m11 == 1 && m22 == 1) || (t33 == 1 && b33 == 1)) && m33 == 0)
        {
            mid3.setImageResource(R.drawable.egg);
            return 5;
        }
        else if (((b22 == 1 && b33 == 1) || (t11 == 1 && m11 == 1) || (m22 == 1 && t33 == 1)) && b11 == 0)
        {
            bot1.setImageResource(R.drawable.egg);
            return 6;
        }
        else if (((b11 == 1 && b33 == 1) || (t22 == 1 && m22 == 1)) && b22 == 0)
        {
            bot2.setImageResource(R.drawable.egg);
            return 7;
        }
        else if (((b11 == 1 && b22 == 1) || (t11 == 1 && m22 == 1) || (t33 == 1 && m33 == 1)) && b33 == 0)
        {
            bot3.setImageResource(R.drawable.egg);
            return 8;
        }
        else
        {
            Boolean flag = false;

            do
            {
                selection = randomNum();

                if ((selection == 0 && !t1m) || (selection == 1 && !t2m) || (selection == 2 && !t3m) ||
                    (selection == 3 && !m1m) || (selection == 4 && !m2m) || (selection == 5 && !m3m) ||
                    (selection == 6 && !b1m) || (selection == 7 && !b2m) || (selection == 8 && !b3m))
                {
                    flag = true;
                }

                if (t1m && t2m && t3m && m1m && m2m && m3m && b1m && b2m && b3m)
                    flag = true;

            } while (!flag);
        }
        return selection;
    }

    public void commenceRobotTakeover()
    {
        int meta = checkWorld();

        switch (meta)
        {
            case 0:
            if (who == 0 && !t1m)
            {
                top1.setImageResource(R.drawable.egg);
                t11 = 1;
                who = 1;
                t1m = true;
                checkAll(0);
            }
            else if (who == 1 && !t1m)
            {
                top1.setImageResource(R.drawable.grape);
                t11 = 2;
                who = 0;
                t1m = true;
                checkAll(1);
            }
            break;
            case 1:
                if (who == 0 && !t2m)
                {
                    top2.setImageResource(R.drawable.egg);
                    t22 = 1;
                    who = 1;
                    t2m = true;
                    checkAll(0);
                }
                else if (who == 1 && !t2m)
                {
                    top2.setImageResource(R.drawable.grape);
                    t22 = 2;
                    who = 0;
                    t2m = true;
                    checkAll(1);
                }
                break;
            case 2:
                if (who == 0 && !t3m)
                {
                    top3.setImageResource(R.drawable.egg);
                    t33 = 1;
                    who = 1;
                    t3m = true;
                    checkAll(0);
                }
                else if (who == 1 && !t3m)
                {
                    top3.setImageResource(R.drawable.grape);
                    t33 = 2;
                    who = 0;
                    t3m = true;
                    checkAll(1);
                }
                break;
            case 3:
                if (who == 0 && !m1m)
                {
                    mid1.setImageResource(R.drawable.egg);
                    m11 = 1;
                    who = 1;
                    m1m = true;
                    checkAll(0);
                }
                else if (who == 1 && !m1m)
                {
                    mid1.setImageResource(R.drawable.grape);
                    m11 = 2;
                    who = 0;
                    m1m = true;
                    checkAll(1);
                }
                break;
            case 4:
                if (who == 0 && !m2m)
                {
                    mid2.setImageResource(R.drawable.egg);
                    m22 = 1;
                    who = 1;
                    m2m = true;
                    checkAll(0);
                }
                else if (who == 1 && !m2m)
                {
                    mid2.setImageResource(R.drawable.grape);
                    m22 = 2;
                    who = 0;
                    m2m = true;
                    checkAll(1);
                }
                break;
            case 5:
                if (who == 0 && !m3m)
                {
                    mid3.setImageResource(R.drawable.egg);
                    m33 = 1;
                    who = 1;
                    m3m = true;
                    checkAll(0);
                }
                else if (who == 1 && !m3m)
                {
                    mid3.setImageResource(R.drawable.grape);
                    m33 = 2;
                    who = 0;
                    m3m = true;
                    checkAll(1);
                }
                break;
            case 6:
                if (who == 0 && !b1m) {
                    bot1.setImageResource(R.drawable.egg);
                    b11 = 1;
                    who = 1;
                    b1m = true;
                    checkAll(0);
                }
                else if (who == 1 && !b1m)
                {
                    bot1.setImageResource(R.drawable.grape);
                    b11 = 2;
                    who = 0;
                    b1m = true;
                    checkAll(1);
                }
                break;
            case 7:
                if (who == 0 && !b2m) {
                    bot2.setImageResource(R.drawable.egg);
                    b22 = 1;
                    who = 1;
                    b2m = true;
                    checkAll(0);
                }
                else if (who == 1 && !b2m)
                {
                    bot2.setImageResource(R.drawable.grape);
                    b22 = 2;
                    who = 0;
                    b2m = true;
                    checkAll(1);
                }
                break;
            case 8:
                if (who == 0 && !b3m) {
                    bot3.setImageResource(R.drawable.egg);
                    b33 = 1;
                    who = 1;
                    b3m = true;
                    checkAll(0);
                }
                else if (who == 1 && !b3m)
                {
                    bot3.setImageResource(R.drawable.grape);
                    b33 = 2;
                    who = 0;
                    b3m = true;
                    checkAll(1);
                }
                break;
        }
    }

    public int randomNum ()
    {
        Random ran = new Random();
        int num = ran.nextInt(9-0);

        return num;
    }
    // lol
    public void openBrowser(View view){

        //Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }
}
