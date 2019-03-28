package com.example.cedri.camera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ImagesBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";

    private static final String TABLE_IMAGES = "table_images";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_CHEMIN = "Chemin";
    private static final int NUM_COL_CHEMIN = 1;

    private SQLiteDatabase bdd;

    private MaBaseSqLite maBaseSqLite;

    public ImagesBDD(Context context){
        maBaseSqLite = new MaBaseSqLite(context,NOM_BDD,null,VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSqLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertImage(Image image){
        ContentValues values = new ContentValues();

        values.put(COL_CHEMIN,image.getChemin());

        return bdd.insert(TABLE_IMAGES,null,values);
    }

    public int updateImage(int id, Image image){
        ContentValues values = new ContentValues();

        values.put(COL_CHEMIN,image.getChemin());

        return bdd.update(TABLE_IMAGES,values,COL_ID + " = " +id,null);
    }

    public int removeImageWithID(int id){
        return bdd.delete(TABLE_IMAGES,COL_ID + " = " +id,null);
    }

    public Image getImageWithChemin(String chemin){
        Cursor c = bdd.query(TABLE_IMAGES, new String[] {COL_ID,COL_CHEMIN},COL_CHEMIN + " LIKE \"" +
                chemin + "\"",null,null,null,null);
        return cursorToImage(c);
    }

    private Image cursorToImage(Cursor c){
        if (c.getCount() == 0){
            return null;
        }

        c.moveToFirst();
        Image image = new Image();

        image.setId(c.getInt(NUM_COL_ID));
        image.setChemin(c.getString(NUM_COL_CHEMIN));

        c.close();

        return image;
    }
}
