package com.example.simpleclickinteraction2.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpleclickinteraction2.R;

public class LevelsActivity extends Activity {

    private GridView gridView;
    private String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainLayout);
        mainLayout.setGravity(Gravity.CENTER_VERTICAL);

        // Creating a GridView dynamically
        gridView = new GridView(this);
        gridView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(8);
        gridView.setHorizontalSpacing(8);
        gridView.setGravity(Gravity.CENTER);

        mainLayout.addView(gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, levels) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setBackgroundResource(R.drawable.rounded_button); // Set button background
                textView.setTextColor(Color.WHITE); // Set text color
                textView.setGravity(Gravity.CENTER); // Center text horizontally and vertically
                textView.setPadding(16, 16, 16, 16); // Add padding to the text
                return textView;
            }
        };

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedLevel = levels[position];
                Toast.makeText(LevelsActivity.this, "Selected Level: " + selectedLevel, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LevelsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
