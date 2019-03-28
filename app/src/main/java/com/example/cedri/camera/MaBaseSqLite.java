package com.example.cedri.camera;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSqLite extends SQLiteOpenHelper {
    private static final String TABLE_IMAGES = "table_images";
    private static final String COL_ID = "ID";
    private static final String COL_CHEMIN = "Chemin";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_IMAGES + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_CHEMIN + " TEXT NOT NULL);";

    public MaBaseSqLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE " + TABLE_IMAGES + ";");
        onCreate(db);
    }
}
