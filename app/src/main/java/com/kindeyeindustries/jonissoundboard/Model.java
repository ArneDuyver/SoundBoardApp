package com.kindeyeindustries.jonissoundboard;

import java.util.ArrayList;

public class Model {
    private String directoryPath;
    private ArrayList<Sound> sounds;
    private static Model instance;

    public static Model getInstance(String directoryPath, ArrayList<Sound> sounds){
        if (instance == null){
            instance = new Model(directoryPath,sounds);
        }
        return instance;
    }

    public static Model getInstance(){
        if (instance == null){
            return instance = new Model(null, null);
        }
        return instance;
    }

    private Model(String directoryPath, ArrayList<Sound> sounds) {
        this.directoryPath = directoryPath;
        this.sounds = sounds;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String p) {
        this.directoryPath = p;
    }

    public ArrayList<Sound> getSounds() {
        return sounds;
    }

    public void addSound(Sound s){
        sounds.add(s);
    }

    public void removeSound(int position){
        sounds.remove(position);
    }
}
