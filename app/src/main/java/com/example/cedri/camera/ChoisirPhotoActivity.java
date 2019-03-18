package com.example.cedri.camera;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChoisirPhotoActivity extends AppCompatActivity {
    private static final int RETOUR_IMPORT_PHOTO = 1;
    private static final int RETOUR_PRENDRE_PHOTO = 2;

    private Button btnPhoto;
    private Button impPhoto;
    private String photoPath = null;
    private ImageView photo;
    private ImageView add;
    private Bitmap image = null;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_photo);
        initActivity();
    }

    private void initActivity() {
        btnPhoto = (Button) findViewById(R.id.prendrePhoto);
        impPhoto = (Button) findViewById(R.id.importPhoto);
        photo = (ImageView) findViewById(R.id.photo);
        add = (ImageView)findViewById(R.id.add);

        intent = new Intent(ChoisirPhotoActivity.this,JeuxActivity.class);
        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoPath != null) {
                    intent.putExtra("pathPhoto",photoPath);
                    startActivity(intent);
                }
            }
        });
        impPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RETOUR_IMPORT_PHOTO);
            }
        });
        btnPhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendrePhoto();
            }
        });
    }

    private void prendrePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo" + time, ".jpg", photoDir);
                photoPath = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(ChoisirPhotoActivity.this,
                        ChoisirPhotoActivity.this.getApplicationContext().getPackageName() + ".provider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, RETOUR_PRENDRE_PHOTO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RETOUR_PRENDRE_PHOTO && resultCode == RESULT_OK) {
            image = BitmapFactory.decodeFile(photoPath);
            photo.setImageBitmap(image);
        }

        if (requestCode == RETOUR_IMPORT_PHOTO && resultCode == RESULT_OK) {
            Uri filePath = data.getData();
            photoPath = getPath(filePath);
            image = BitmapFactory.decodeFile(photoPath);
            photo.setImageBitmap(image);
           try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                photo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}