package com.example.simpleclickinteraction2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.simpleclickinteraction2.R;

public class StartPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }

    public void startLevelsActivity(View view) {
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }
}
