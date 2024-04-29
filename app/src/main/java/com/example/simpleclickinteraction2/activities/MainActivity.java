package com.example.simpleclickinteraction2.activities;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.example.simpleclickinteraction2.CanvasController;
import com.example.simpleclickinteraction2.R;
import com.example.simpleclickinteraction2.ui.theme.ColorKt;

public class   MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the main layout as a LinearLayout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainLayout);
        View view =this.getWindow().getDecorView();
        view.setBackgroundColor(ContextCompat.getColor(this.getApplicationContext(),R.color.dark_gray));

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

        buttonParams.setMargins(0,50,0,50);

        // Create the buttons
        Button restart = new Button(this);
        restart.setText("Restart");
        restart.setTextColor(Color.WHITE);
        restart.setBackgroundColor(Color.TRANSPARENT);
        restart.setCompoundDrawablesWithIntrinsicBounds(null,getDrawable(R.drawable.restart_button),null,null);
        restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               canvasController.restart();
            }
        });

        Button undo = new Button(this);
        undo.setText("Undo");
        undo.setTextColor(Color.WHITE);
        undo.setBackgroundColor(Color.TRANSPARENT);
        undo.setCompoundDrawablesWithIntrinsicBounds(null,getDrawable(R.drawable.undo_button),null,null);
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                canvasController.undo();
            }
        });

        //the hint button
        Button hint = new Button(this);
        hint.setText("Hint");
        hint.setTextColor(Color.WHITE);
        hint.setBackgroundColor(Color.TRANSPARENT);
        hint.setCompoundDrawablesWithIntrinsicBounds(null,getDrawable(R.drawable.hint_button),null,null);
        hint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                canvasController.hint();
            }
        });

        // Add buttons to the buttons layout
        buttonsLayout.addView(restart, buttonParams);
        buttonsLayout.addView(undo, buttonParams);
        buttonsLayout.addView(hint,buttonParams);

        // Add the buttons layout to the main layout
        mainLayout.addView(buttonsLayout);

    }
}
