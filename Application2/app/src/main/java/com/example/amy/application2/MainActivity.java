package com.example.amy.application2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Painting paintView;
    public static String imgLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = (Painting) findViewById(R.id.painting);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    public void CLEAR(View v){
        paintView.clear();
    }

    public void CONTINUE(View v){
        saveBitMap(MainActivity.this, paintView);
        Intent intent = new Intent(this, OptionsMenuActivity.class);
        intent.putExtra("a", imgLocation);
        startActivity(intent);
    }

    public void red(View v){
        paintView.changeColorToRed();
    }
    public void blue(View v){
        paintView.changeColorToBlue();
    }
    public void green(View v){
        paintView.changeColorToGreen();
    }
    public void yellow(View v){
        paintView.changeColorToYellow();
    }
    public void grey(View v){
        paintView.changeColorToGrey();
    }
    public void black(View v){
        paintView.changeColorToBlack();
    }
    public void white(View v) { paintView.changeColorToWhite(); }
    public void magenta(View v) { paintView.changeColorToMagenta(); }
    public void darkGreen(View v) { paintView.changeColorToDarkGreen(); }

    private Bitmap getBitmapFromView(View view) {
        //Defines a bitmap of the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable!=null) {
            //if background is not null, draw it on the canvas
            bgDrawable.draw(canvas);
        }
        else {
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        return returnedBitmap;
    }

    private File saveBitMap(Context context, View drawView){
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Rolig Due");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("TAG", "Can't create directory to save image");
            return null;
        }
        //String filename = pictureFileDir.getPath() + File.separator + System.currentTimeMillis()+".jpg";
        String filename = pictureFileDir.getPath() + File.separator + "temporary_image.jpg";
        imgLocation = pictureFileDir.getPath();
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery( context,pictureFile.getAbsolutePath());
        return pictureFile;
    }
    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue scanning gallery.");
        }
    }
}