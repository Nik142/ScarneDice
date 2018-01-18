package com.example.nikhil.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import android.os.Handler;

import android.content.Context;

public class MainActivity extends AppCompatActivity {

    int userTotalScore = 0, userTurnScore = 0, userOverallScore = 0, compOverallScore = 0, compTurnScore = 0, compTotalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void roll(View view) {
        Random rand = new Random();
        int turn, turn1, temp;
        turn = 1 + (rand.nextInt(6));
        turn1 = 1 + (rand.nextInt(6));

        ImageView image = (ImageView) findViewById(R.id.imageView);
        ImageView image1 = (ImageView) findViewById(R.id.image2View);

        Button roll = (Button) findViewById(R.id.roll_button_view);
        Button hold = (Button) findViewById(R.id.hold_button_view);

        Context context = getApplicationContext();
        CharSequence text = "User got 1 on right dice!";
        CharSequence text2 = "User have to roll again";
        CharSequence text3 = "User got 1 on left dice";
        CharSequence text4 = "User got 1 on both dices";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        Toast toast2 = Toast.makeText(context, text2, duration);
        Toast toast3 = Toast.makeText(context, text3, duration);
        Toast toast4 = Toast.makeText(context, text4, duration);


        switch (turn) {
            case 1:
                image.setImageResource(R.drawable.dice1);
                break;
            case 2:
                image.setImageResource(R.drawable.dice2);
                break;
            case 3:
                image.setImageResource(R.drawable.dice3);
                break;
            case 4:
                image.setImageResource(R.drawable.dice4);
                break;
            case 5:
                image.setImageResource(R.drawable.dice5);
                break;
            case 6:
                image.setImageResource(R.drawable.dice6);
        }

        switch (turn1) {
            case 1:
                image1.setImageResource(R.drawable.dice1);
                break;
            case 2:
                image1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                image1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                image1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                image1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                image1.setImageResource(R.drawable.dice6);
        }

        temp = userOverallScore;
        if (turn != 1 && turn1 != 1) {

            if (turn != turn1) {
                userTurnScore = turn + turn1;
                userTotalScore += userTurnScore;
                hold.setEnabled(true);
            } else {

                userTurnScore = turn + turn1;
                userTotalScore += userTurnScore;
                hold.setEnabled(false);
                toast2.show();
            }
        } else if (turn == 1 && turn1 == 1) {
            userOverallScore = 0;
            userTotalScore = 0;
            userTurnScore = 0;
            hold.setEnabled(true);
            toast4.show();
            display();
            computerTurn();
        }

        if (turn == 1 && turn1 != 1) {

            userTurnScore = 0;
            userTotalScore = 0;
            hold.setEnabled(true);
            toast3.show();
            display();
            computerTurn();
        }

        if (turn != 1 && turn1 == 1) {

            userOverallScore = temp;
            userTurnScore = 0;
            userTotalScore = 0;
            toast.show();
            display();
            computerTurn();
        }

        display();

        if ((userOverallScore + userTotalScore) >= 100) {

            userOverallScore += userTotalScore;
            display();
            Context context1 = getApplicationContext();
            CharSequence text1 = "User Won!";
            int duration1 = Toast.LENGTH_SHORT;

            Toast toast1 = Toast.makeText(context1, text1, duration1);
            toast1.show();

            roll.setEnabled(false);
            hold.setEnabled(false);
        }
    }

    public void hold(View view) {

        userOverallScore += userTotalScore;
        userTotalScore = 0;
        userTurnScore = 0;
        computerTurn();
        display1();
    }

    public void reset(View view) {

        userTotalScore = 0;
        userTurnScore = 0;
        compTotalScore = 0;
        userOverallScore = 0;
        compTurnScore = 0;
        compOverallScore = 0;
        display();

        Button roll = (Button) findViewById(R.id.roll_button_view);
        roll.setEnabled(true);

        Button hold = (Button) findViewById(R.id.hold_button_view);
        hold.setEnabled(true);
    }

    private void display() {

        TextView text = (TextView) findViewById(R.id.text_view);
        text.setText("Your score : " + userOverallScore + "   Computer score : " + compTotalScore + "\nYour turn score : " + userTotalScore);
    }

    private void display1() {
        TextView text = (TextView) findViewById(R.id.text_view);
        text.setText("Your score : " + userOverallScore + "   Computer score : " + compTotalScore + "\nComputer turn score : " + compOverallScore);

    }

    private int computerTurn() {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                        Button roll = (Button) findViewById(R.id.roll_button_view);
                        roll.setEnabled(false);

                        Button hold = (Button) findViewById(R.id.hold_button_view);
                        hold.setEnabled(false);

                        Random rand1 = new Random();
                        int turn1, turn2;

                        Context context = getApplicationContext();
                        CharSequence text = "Computer got 1 on left dice!";
                        CharSequence text2 = "Computer got 1 on right dice";
                        CharSequence text3 = "Computer got 1 on both dices";
                        CharSequence text4 = "It's the user's turn now";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        Toast toast2 = Toast.makeText(context, text2, duration);
                        Toast toast3 = Toast.makeText(context, text3, duration);
                        Toast toast4 = Toast.makeText(context, text4, duration);

                        ImageView image1 = (ImageView) findViewById(R.id.imageView);
                        ImageView image2 = (ImageView) findViewById(R.id.image2View);

                        turn1 = 1 + (rand1.nextInt(6));
                        turn2 = 1 + (rand1.nextInt(6));

                        if (compOverallScore < 20 && compTotalScore < 100) {
                            switch (turn1) {
                                case 1:
                                    image1.setImageResource(R.drawable.dice1);
                                    break;
                                case 2:
                                    image1.setImageResource(R.drawable.dice2);
                                    break;
                                case 3:
                                    image1.setImageResource(R.drawable.dice3);
                                    break;
                                case 4:
                                    image1.setImageResource(R.drawable.dice4);
                                    break;
                                case 5:
                                    image1.setImageResource(R.drawable.dice5);
                                    break;
                                case 6:
                                    image1.setImageResource(R.drawable.dice6);
                            }

                            switch (turn2) {
                                case 1:
                                    image2.setImageResource(R.drawable.dice1);
                                    break;
                                case 2:
                                    image2.setImageResource(R.drawable.dice2);
                                    break;
                                case 3:
                                    image2.setImageResource(R.drawable.dice3);
                                    break;
                                case 4:
                                    image2.setImageResource(R.drawable.dice4);
                                    break;
                                case 5:
                                    image2.setImageResource(R.drawable.dice5);
                                    break;
                                case 6:
                                    image2.setImageResource(R.drawable.dice6);
                            }

                            if (turn1 != 1 && turn2 != 1) {

                                if (turn1 != turn2) {

                                    compTurnScore = turn1 + turn2;
                                    compOverallScore += compTurnScore;
                                    display1();
                                    computerTurn();

                                } else {

                                    compTurnScore = turn1 + turn2;
                                    compOverallScore += compTurnScore;
                                    display1();
                                    computerTurn();

                                }
                            } else if (turn1 == 1 && turn2 == 1) {

                                compTotalScore = 0;
                                compOverallScore = 0;
                                compTurnScore = 0;
                                toast3.show();
                                toast4.show();
                                display1();
                                roll.setEnabled(true);
                                hold.setEnabled(true);
                            }

                            if (turn1 == 1 && turn2 != 1) {
                                compTotalScore += compOverallScore;
                                compOverallScore = 0;
                                compTurnScore = 0;
                                display1();
                                toast.show();
                                toast4.show();
                                roll.setEnabled(true);
                                hold.setEnabled(true);
                            }

                            if (turn1 != 1 && turn2 == 1) {

                                compTotalScore += compOverallScore;
                                compOverallScore = 0;
                                compTurnScore = 0;
                                display1();
                                toast2.show();
                                toast4.show();
                                roll.setEnabled(true);
                                hold.setEnabled(true);
                            }

                        } else {

                            compTotalScore += compOverallScore;
                            display1();
                            toast4.show();
                            compOverallScore = 0;
                            roll.setEnabled(true);
                            hold.setEnabled(true);
                        }

                        if (compTotalScore >= 100) {

                            display();
                            Context context1 = getApplicationContext();
                            CharSequence text1 = "Computer Won!";
                            int duration1 = Toast.LENGTH_SHORT;

                            Toast toast1 = Toast.makeText(context1, text1, duration1);
                            toast1.show();

                            roll.setEnabled(false);
                            hold.setEnabled(false);
                        }
                    }
                }, 1000);

            }
        });

        return 0;
    }

}
