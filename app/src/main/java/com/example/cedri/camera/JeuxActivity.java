package com.example.cedri.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class JeuxActivity extends AppCompatActivity {
    private ImageView photo;
    private Button rouge;
    private Button vert;
    private Button bleu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        Intent intent = getIntent();
        photo = (ImageView)findViewById(R.id.photo);
        if(intent!=null){
            Bitmap image = BitmapFactory.decodeFile(intent.getStringExtra("pathPhoto").toString());
            photo.setImageBitmap(image);
        }
        photo.setOnTouchListener(new ImageView.OnTouchListener(){

            public boolean onTouch(View myView, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_MOVE)
                {
                    int X = (int)event.getX();
                    int Y = (int)event.getY();

                    Bitmap bitmap =((BitmapDrawable)photo.getDrawable()).getBitmap();
                    int pixel = bitmap.getPixel(X,Y);

                }
                return true;
            }

        });
    }
    private void testDessiner(){

    }
}
