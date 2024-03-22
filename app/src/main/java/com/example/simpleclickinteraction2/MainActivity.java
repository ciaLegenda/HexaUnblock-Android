package com.example.simpleclickinteraction2;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class   MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the main layout as a LinearLayout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainLayout);

        // Create the draggable hexagon view
        CanvasController canvasController = new CanvasController(this);

        // Set layout parameters for the draggable hexagon view
        LinearLayout.LayoutParams hexagonParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                3, // Use weight to take remaining space
                4 // Weight for the draggable hexagon view
        );

        // Add the draggable hexagon view to the main layout
        mainLayout.addView(canvasController, hexagonParams);

        // Create a horizontal layout to hold the buttons
        LinearLayout buttonsLayout = new LinearLayout(this);
        buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create layout parameters for the buttons
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                0, // Use weight to distribute space evenly
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1 // Equal weight for both buttons
        );

        buttonParams.setMargins(0,100,0,100);

        // Create the buttons
        Button button1 = new Button(this);
        button1.setText("Button 1");

        Button button2 = new Button(this);
        button2.setText("Button 2");

        // Add buttons to the buttons layout
        buttonsLayout.addView(button1, buttonParams);
        buttonsLayout.addView(button2, buttonParams);

        // Add the buttons layout to the main layout
        mainLayout.addView(buttonsLayout);
    }
}
