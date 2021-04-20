package com.example.prey_predator_game;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends Activity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private GestureDetector aGesture;

    // BOARD INFORMATION
    final int SQUARE = 130;
    final int OFFSET = 70;
    final int COLUMNS = 8;
    final int ROWS = 8;
    final int gameBoard [][] = {
            {1,1,1,1,1,1,1,1},
            {1,2,2,1,2,1,2,1},
            {1,2,2,2,2,2,2,1},
            {1,2,1,2,2,2,2,1},
            {1,2,2,2,2,1,2,1},
            {1,2,2,2,2,2,2,3},
            {1,2,1,2,2,2,2,1},
            {1,1,1,1,1,1,1,1},
    };

    // VISUAL OBJECTS ARE ARGANIZED IN AN ARRAY LIST
    private ArrayList<ImageView> visualObjects;
    Player player;
    Enemy enemy;

    // LAYOUT AND INTERACTIVE INFORMATION
    private RelativeLayout relativeLayout;
    private ImageView enemyIMG;
    private ImageView playerIMG;
    private ImageView obstacleIMG;
    private ImageView exitIMG;
    private int exitRow;
    private int exitCol;

    // WINS AND LOSSES
    private int wins;
    private int losses;
    private TextView winsTextView;
    private TextView lossesTextView;

    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TASK 1: SET THE LAYOUT CONTENT FOR THE ACTIVITY
        setContentView(R.layout.activity_main);

        // TASK 2: REFERENCE THE ACTIVITY LAYOUT AND TEXT VIEWS
        relativeLayout =  (RelativeLayout) findViewById(R.id.relativeLayout);
        winsTextView = (TextView) findViewById(R.id.winsTextView);
        lossesTextView = (TextView) findViewById(R.id.lossesTextView);

        // TASK 3: INSTANTIATE THE LAYOUT INFLATER
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TASK 4: BUILD THE LOGIC BOARD AND CONSTRUCT THE GAME
        visualObjects = new ArrayList<ImageView>();
        buildLogicBoard();
        createEnemy();
        createPlayer();
        wins = 0;
        losses = 0;
        winsTextView.setText("Wins: " + wins);
        lossesTextView.setText("Losses: " + losses);

        // TASK 5: INSTANTIATE A GESTURE DETECTOR
        aGesture = new GestureDetector(this, this);
        aGesture.setOnDoubleTapListener(this);
    }

    private void buildLogicBoard (){
        for (int row = 0; row< ROWS; row++){
            for (int col = 0; col < COLUMNS; col++){
                if (gameBoard[row][col] == BoardCodes.isOBSTACLE){
                    obstacleIMG = (ImageView) layoutInflater.inflate(R.layout.exit_layout, null);
                    obstacleIMG.setX(col*SQUARE+OFFSET);
                    obstacleIMG.setY(row*SQUARE+OFFSET);
                    visualObjects.add(obstacleIMG);
                } else if (gameBoard[row][col] == BoardCodes.isHOME){
                    exitIMG = (ImageView) layoutInflater.inflate(R.layout.exit_layout, null);
                    exitIMG.setX(col*SQUARE+OFFSET);
                    exitIMG.setY(row*SQUARE+OFFSET);
                    visualObjects.add(exitIMG);
                    exitRow = 5;
                    exitCol = 7;
                }
            }
        }
    }

    private void createEnemy () {
        int row = 2;
        int col = 4;
        enemyIMG = (ImageView) layoutInflater.inflate(R.layout.enemy_layout, null);
        enemyIMG.setX(col*SQUARE+OFFSET);
        enemyIMG.setY(row*SQUARE+OFFSET);
        relativeLayout.addView(enemyIMG, 0);

        enemy = new Enemy();
        enemy.setRow(row);
        enemy.setCol(col);
        visualObjects.add(enemyIMG);
    }

    private void createPlayer () {
        int row = 1;
        int col = 1;

        playerIMG = (ImageView) layoutInflater.inflate(R.layout.player_layout, null);
        playerIMG.setX(col*SQUARE+OFFSET);
        playerIMG.setY(row*SQUARE+OFFSET);
        relativeLayout.addView(playerIMG, 0);
        player = new Player();
        player.setRow(row);
        player.setCol(col);
        visualObjects.add(playerIMG);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return aGesture.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        movePlayer(velocityX, velocityY);
        return true;
    }

    private void movePlayer (float velocityX, float velocityY){
        String direction = "undetectable";

        // TASK 1: MOVE THE PLAYER IN THE FLING DIRECTION
        if (velocityX > 2500){
            direction = "RIGHT";
        } else if (velocityX < -2500){
            direction = "LEFT";
        } else if (velocityY > 2500){
            direction = "DOWN";
        } else if (velocityY < -2500){
            direction = "UP";
        }

        if (!direction.contains("undetectable")){
            player.move(gameBoard, direction);
            playerIMG.setX(player.getCol()*SQUARE+OFFSET);
            playerIMG.setY(player.getRow()*SQUARE+OFFSET);
            Log.v("Player movement", "row=" + player.getCol() + " col=" + player.getRow());

            // TASK 2 IT IS NOW THE ENEMY'S TURN. MOVE THE ENEMY
            enemy.move(gameBoard, player.getCol(), player.getRow());
            enemyIMG.setX(enemy.getCol() * SQUARE + OFFSET);
            enemyIMG.setY(enemy.getRow() * SQUARE + OFFSET);
        }

        // TASK 2: CHECK IF THE GAME IS OVER
        // CHECK IF ENEMY CATCHES PLAYER
        if (enemy.getCol() == player.getCol() && enemy.getRow() == player.getRow()){
            losses++;
            lossesTextView.setText("Losses: " + losses);
            startNewGame();
        } else if (exitRow == player.getRow() && exitCol == player.getCol()){
            wins++;
            winsTextView.setText("Wins: " + wins);
            startNewGame();
        }
    }

    private void startNewGame () {
        // TASK 1: CLEAR THE BOARD AND REMOVE PLAYERS
        int howMany = visualObjects.size();
        for (int i = 0; i<howMany; i++){
            ImageView visualObj = visualObjects.get(i);
            relativeLayout.removeView(visualObj);
        }

        visualObjects.clear();

        // TASK 2: REBUILD THE BOARD
        buildLogicBoard();

        // TASK 3: ADD THE PLAYERS
        createEnemy();
        createPlayer();
    }
}