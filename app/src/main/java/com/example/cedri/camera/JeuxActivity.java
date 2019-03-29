package com.example.cedri.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JeuxActivity extends AppCompatActivity {
    private PaintView paintView;
    private Drawable photo;
    private ImageView upload;
    private int cpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        Intent intent = getIntent();
        if(intent!=null){
            Bitmap image = BitmapFactory.decodeFile(intent.getStringExtra("pathPhoto").toString());
            photo = new BitmapDrawable(getResources(), image);
            setBackground(findViewById(R.id.jeuxActivity),photo);
        }

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        upload = (ImageView) findViewById(R.id.upload);
        cpt = 1;
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap capturedBitmap = Screenshot.takescreenshotOfRootView(findViewById(R.id.jeuxActivity));
                File file = new File(getApplicationContext().getExternalFilesDir(
                        Environment.DIRECTORY_DOWNLOADS), "GALERYE");
                if (!file.mkdirs()) {
                    Log.e("1", "Directory not created");
                }
                File image = new File(file, "image" + cpt + ".jpg");
                cpt++;
                System.out.print(image.getAbsolutePath());
                try{

                    FileOutputStream img = new FileOutputStream(image);

                    capturedBitmap.compress(Bitmap.CompressFormat.PNG, 100, img);
                    img.close();


                    System.out.println("File Saved");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setBackground(View v, Drawable drawable){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            v.setBackground(drawable);
        else
            v.setBackgroundDrawable(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.red:
                paintView.red();
                return true;
            case R.id.blue:
                paintView.blue();
                return true;
            case R.id.green:
                paintView.green();
                return true;
            case R.id.yellow:
                paintView.yellow();
                return true;
            case R.id.cyan:
                paintView.cyan();
                return true;
            case R.id.magenta:
                paintView.pink();
                return true;
            case R.id.white:
                paintView.white();
                return true;
            case R.id.black:
                paintView.black();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
