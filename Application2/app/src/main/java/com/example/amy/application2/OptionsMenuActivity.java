package com.example.amy.application2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class OptionsMenuActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionsmenu);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }
    int selection = 0;
    public void shred(View v){
        Intent intent = new Intent(this, ResultingActivity.class);
        selection = 1;
        intent.putExtra("selection", selection);
        startActivity(intent);
    }
    public void feedCrocs(View v){
        Intent intent = new Intent(this, ResultingActivity.class);
        selection = 2;
        intent.putExtra("selection", selection);
        startActivity(intent);
    }
    public void launchIntoSun(View v){
        Intent intent = new Intent(this, ResultingActivity.class);
        selection = 3;
        intent.putExtra("selection", selection);
        startActivity(intent);
    }
    public void bury(View v){
        Intent intent = new Intent(this, ResultingActivity.class);
        selection = 4;
        intent.putExtra("selection", selection);
        startActivity(intent);
    }
}
