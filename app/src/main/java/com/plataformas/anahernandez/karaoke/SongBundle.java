package com.plataformas.anahernandez.karaoke;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anahernandez on 5/21/18.
 */

public class SongBundle implements Parcelable{

    private String songRaw;
    private String imageRaw;
    private String songName;
    private String songArtist;

    public SongBundle(String songRaw, String imageRaw, String songName, String songArtist) {
        this.songRaw = songRaw;
        this.imageRaw = imageRaw;
        this.songName = songName;
        this.songArtist = songArtist;
    }

    protected SongBundle(Parcel in) {
        songRaw = in.readString();
        imageRaw = in.readString();
        songName = in.readString();
        songArtist = in.readString();
    }

    public static final Creator<SongBundle> CREATOR = new Creator<SongBundle>() {
        @Override
        public SongBundle createFromParcel(Parcel in) {
            return new SongBundle(in);
        }

        @Override
        public SongBundle[] newArray(int size) {
            return new SongBundle[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songRaw);
        dest.writeString(imageRaw);
        dest.writeString(songName);
        dest.writeString(songArtist);
    }
    public String toString()
    {
        return "Nombre de la canción: "+ songName+ "\nArtista: "+songArtist;
    }

}
