package com.kindeyeindustries.jonissoundboard;

import android.os.Parcel;
import android.os.Parcelable;

public class Sound {
    private String filename, name;

    public Sound(String filename, String name) {
        this.filename = filename;
        this.name = name;
    }

    protected Sound(Parcel in) {
        filename = in.readString();
        name = in.readString();
    }
    public String getFilename() {
        return filename;
    }

    public String getName() {
        return name;
    }

}
