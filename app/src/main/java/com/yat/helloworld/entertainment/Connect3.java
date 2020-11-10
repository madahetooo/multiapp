package com.yat.helloworld.entertainment;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yat.helloworld.R;

import static com.yat.helloworld.R.drawable.o;
import static com.yat.helloworld.R.drawable.x;

public class Connect3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);
    }

    int activePlayer = 0;  // 0 = O  , 1 = X
    boolean gameIsActive = true;
    boolean someOneHasWon = false;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};   // 2 means UnPlayed

    int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};  // winning Position

    public void dropIn(View view) {
        ImageView xoImage = (ImageView) view;  //casting / find view
        int tappedXOImage = Integer.parseInt(xoImage.getTag().toString());


        // who will start
        if (gameState[tappedXOImage] == 2 && gameIsActive) { //check if there is available place && game is running
            gameState[tappedXOImage] = activePlayer;  // set active player in empty place
//            xoImage.setTranslationY(-1000f);  //transition
            if (activePlayer == 0) {  // if active player == O
                xoImage.setImageResource(o);
                Log.v("test", "Hello");
                activePlayer = 1;  // active player = X
            } else {
                xoImage.setImageResource(x);
                activePlayer = 0;  // active player = O
            }
//            xoImage.animate().translationYBy(1000f).rotation(360).setDuration(1000); // animation
        }

        // Check for winners
        for (int[] winningPositions : winningPositions) {
            if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]]
                    && gameState[winningPositions[0]] != 2) {  //someone is won
                gameIsActive = false;
                someOneHasWon=true;
                String winner = "X";
                if (gameState[winningPositions[0]] == 0) {
                    winner = "O";
                }
                TextView txWinner = findViewById(R.id.tx_winner);
                txWinner.setText("the winner is " + winner);
                LinearLayout winnerLayout = findViewById(R.id.winner_layout);
                winnerLayout.setVisibility(View.VISIBLE);

            }
            else {
                boolean gameIsOver = true ;

                for (int state : gameState){
                    if (state ==2){
                        gameIsOver =false;
                        break;
                    }
                }
                if (gameIsOver && !someOneHasWon){
                    TextView winnerMessage=findViewById(R.id.tx_winner);
                    winnerMessage.setText("It's Draw" );

                    LinearLayout layout=findViewById(R.id.winner_layout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view) {

        gameIsActive = true;
        LinearLayout winnerLayout = findViewById(R.id.winner_layout);
        winnerLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.grid_layout);

        for (int i =0 ; i< gridLayout.getChildCount(); i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }



    }
}