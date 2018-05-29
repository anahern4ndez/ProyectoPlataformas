package com.plataformas.anahernandez.karaoke;

/**
 * Created by anahernandez on 5/21/18.
 */

public class SongBundle {

    private String songRaw;
    private String imageRaw;
    private String songName;
    private String songArtist;

    public SongBundle(String songName, String imageName)
    {
        this.imageRaw = imageName;
        this.songRaw = songName;
    }

    public String getSongRaw() {
        return songRaw;
    }

    public void setSongRaw(String songRaw) {
        this.songRaw = songRaw;
    }

    public String getImageRaw() {
        return imageRaw;
    }

    public void setImageRaw(String imageRaw) {
        this.imageRaw = imageRaw;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }
}
