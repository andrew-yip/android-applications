package com.example.fibonacci_flower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> allPetals;
    private LayoutInflater layoutInflater;

    private Button pinkBtn;
    private Button goldBtn;
    private Button clearBtn;
    private RelativeLayout relativeLayout;

    Flower myFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFlower = new Flower();
        allPetals = new ArrayList<ImageView>();

        // INITIALIZE THE GENERATION OF THE FIBONACCI ARTWORK
        initialize();

        // CREATE A LAYOUT INFLATER TO ADD PETALS TO RELATIVE LAYOUT
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        pinkBtn = (Button) findViewById(R.id.button1);
        goldBtn = (Button) findViewById(R.id.button2);
        clearBtn = (Button) findViewById(R.id.button3);
        pinkBtn.setOnClickListener(addPetal);
        goldBtn.setOnClickListener(addPetal);
        clearBtn.setOnClickListener(clearPetals);

        // SET THE CENTER COORDINATE
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        myFlower.set_xCenter(metrics.widthPixels/2);
        myFlower.set_yCenter(metrics.heightPixels/2);
    }

    private void initialize () {
        // TASK 1: INITIALIZE THE SETTINGS FOR THE FIRST PETAL

        myFlower.setRotate(0);
        myFlower.setScaleX((float) .3);
        myFlower.setScaleY((float) .3);
        myFlower.setDegenerate((float) 1.001);
        myFlower.initializeAngle();
    }

    // ON CLICK BUTTON HANDLER FOR ADDING A FLOWER PETAL TO THE SCREEN
    private View.OnClickListener addPetal = new View.OnClickListener () {

        public void onClick (View view) {
            // TASK 1: INSTANTIATE A VIEW TO STORE A PETAL GRAPHIC
            ImageView petal;

            // INFLATE THE CORRECT PETAL
            String buttonText = ((Button) view).getText().toString();
            if (buttonText.equals("Add pink")){
                petal = (ImageView) layoutInflater.inflate(R.layout.petal_pink, null);
            } else {
                petal = (ImageView) layoutInflater.inflate(R.layout.petal_gold, null);
            }

            // TASK 2: SET THE VISUAL PROPERTIES OF THE PETAL (METHODS OF IMAGE VIEW CLASS)
            petal.setX(myFlower.get_xCenter());
            petal.setY(myFlower.get_yCenter());
            petal.setPivotX(0);
            petal.setPivotY(100);
            petal.setScaleX(myFlower.getScaleX());
            petal.setScaleY(myFlower.getScaleY());
            petal.setRotation(myFlower.getRotate());

            // TASK 3: PLACE THE INFLATED IMAGEVIEW IN THE MAIN LAYOUT
            relativeLayout.addView(petal, 0);

            // TASK 4: ADD THE IMAGEVIEW OF THE PETAL TO THE ARRAYLIST
            allPetals.add(petal);

            // TASK 5: UPDATE THE ANGLE AND SCALE FOR THE NEXT PETAL TO BE ADDED
            myFlower.updatePetalValues();
        }
    };

    // ON CLICK BUTTON HANDLER TO CLEAR ALL PETALS ON THE SCREEN
    private View.OnClickListener clearPetals = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
             // TASK 1: REMOVE ALL PETAL IMAGE VIEW FROM THE LAYOUT
            for (int i = 0; i<allPetals.size(); i++){
                ImageView petal = allPetals.get(i);
                relativeLayout.removeView(petal);
            }

            // TASK 2: CLEAR THE ARRAYLIST AND RESET ALL VARIABLES
            allPetals.clear();
            initialize();
        }
    };
}