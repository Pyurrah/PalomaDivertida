package com.example.amy.application2;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class ResultingActivity extends AppCompatActivity /*implements View.OnTouchListener */{

    public int intValue;
    public static int destroyThreshold;
    public static Rect r1 = new Rect();
    public static Rect r2 = new Rect();
    public static Rect r3 = new Rect();
    public static Rect r4 = new Rect();
    public static Rect r5 = new Rect();
    public static Movable im;// = new Movable();
    public static View destruct;// = new View();
    public static View vanisher;
    public static View wall_1;
    public static View wall_2;
    public static ImageView explosion;
    public static AnimationDrawable explosionAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_result);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        intValue = getIntent().getIntExtra("selection", 0);

        SurfaceView img = (SurfaceView) findViewById(R.id.backgroundimage);
        im = (Movable) findViewById(R.id.user_image);
        destruct = (View) findViewById(R.id.destructor);
        vanisher = (View) findViewById(R.id.disappearer);
        wall_1 = (View) findViewById(R.id.wall1);
        wall_2 = (View) findViewById(R.id.wall2);
        explosion = (ImageView) findViewById(R.id.shatter);

        if (intValue == 1){
            img.setBackgroundResource(R.mipmap.background_house);
            ResultingActivity.explosion.setBackgroundResource(R.drawable.shatter_house);
            explosionAnimation = (AnimationDrawable)ResultingActivity.explosion.getDrawable();
        }
        if (intValue == 2){
            img.setBackgroundResource(R.mipmap.background_pond);
            ResultingActivity.explosion.setBackgroundResource(R.drawable.shatter_pond);
            explosionAnimation = (AnimationDrawable)ResultingActivity.explosion.getDrawable();
        }
        if (intValue == 3){
            img.setBackgroundResource(R.mipmap.background_space);
            ResultingActivity.explosion.setBackgroundResource(R.drawable.shatter_space);
            explosionAnimation = (AnimationDrawable)ResultingActivity.explosion.getDrawable();
        }
        if (intValue == 4){
            img.setBackgroundResource(R.mipmap.background_dirt);
            ResultingActivity.explosion.setBackgroundResource(R.drawable.shatter_dirt);
            explosionAnimation = (AnimationDrawable)ResultingActivity.explosion.getDrawable();
        }
        im.setImageBitmap(BitmapFactory.decodeFile(MainActivity.imgLocation + File.separator + "temporary_image.jpg"));
    }

    public static boolean isDestroyable(View v, Movable m){
        Rect R1=new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        Rect R2=new Rect(m.getLeft(), m.getTop(), m.getRight(), m.getBottom());
        return R1.intersect(R2);
    }

    @Override
    public void onWindowFocusChanged(boolean a) {
        boolean toMove = false;
        ImageView receive = (ImageView) findViewById(R.id.receiver);
        if (intValue == 1){
            toMove = true;
            receive.setBackgroundResource(R.drawable.animation_house_dweller);
        }
        if (intValue == 2){
            toMove = true;
            receive.setBackgroundResource(R.drawable.animation_pond_dweller);
        }
        if (intValue == 3){
            toMove = true;
            receive.setBackgroundResource(R.drawable.animation_space_dweller);
        }
        if (intValue == 4){
            receive.setBackgroundResource(R.drawable.dirt_nothing);
        }
        if(toMove) {
            final AnimationDrawable frameAnimation = (AnimationDrawable) receive.getBackground();
            receive.post(new Runnable() {
             @Override
             public void run() {
                 frameAnimation.start();
             }
             });
        }
    }
}