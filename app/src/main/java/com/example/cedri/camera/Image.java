package com.example.cedri.camera;

import android.provider.ContactsContract;

public class Image {

    private int id;
    private String chemin;


    public Image(String chemin) {
        this.chemin = chemin;
    }

    public Image(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
