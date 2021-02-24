package com.example.c_curve_drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class FractcalView extends View {

    private float x1;
    private float y1;
    private float x2;
    private float y2;
    public int level;

    private Fractal fractal;

    public FractcalView (Context context) {
        super(context);

        // CREATE A FRACTAL OBJECT
        level = 2;
        fractal = new Fractal();
    }

    protected void onDraw (Canvas canvas) {
        // TASK 1: GET THE DIMENSIONS OF THE CANVAS
        x1 = canvas.getWidth()/3;
        y1 = canvas.getHeight()/4;
        x2 = canvas.getWidth() - x1;
        y2 = y1;

        // TASK 2: FILL THE CANVAS WITH WHITE PAINT
        canvas.drawRGB(255, 255, 255);

        // TASK 3: DRAW THE CCURVE
        fractal.drawCurve(canvas, x1, y1, x2, y2, level);
    }
}
