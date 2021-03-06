package com.example.cedri.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class PageAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_page_accueil);

        ImageView play = (ImageView) findViewById(R.id.play);
        ImageView galerie = (ImageView) findViewById(R.id.ButtonGalerie);

        final Intent intent = new Intent(PageAccueil.this, ChoisirPhotoActivity.class);
        final Intent intent2 = new Intent(PageAccueil.this, Galerie.class);
        play.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        galerie.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
}
