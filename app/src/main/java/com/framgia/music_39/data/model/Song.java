package com.framgia.music_39.data.model;

public class Song {
    private String mNameSong;
    private String mNameArtist;
    private String mImageSong;
    private String mLink;
    private String mDuration;
    private String mDownloadLink;
    private String mGenrer;

    public Song(String nameSong, String nameArtist, String imageSong, String link, String duration,
            String downloadLink, String genrer) {
        mNameSong = nameSong;
        mNameArtist = nameArtist;
        mImageSong = imageSong;
        mLink = link;
        mDuration = duration;
        mDownloadLink = downloadLink;
        mGenrer = genrer;
    }

    public Song() {
    }

    public String getNameSong() {
        return mNameSong;
    }

    public void setNameSong(String nameSong) {
        mNameSong = nameSong;
    }

    public String getNameArtist() {
        return mNameArtist;
    }

    public void setNameArtist(String nameArtist) {
        mNameArtist = nameArtist;
    }

    public String getImageSong() {
        return mImageSong;
    }

    public void setImageSong(String imageSong) {
        mImageSong = imageSong;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getDownloadLink() {
        return mDownloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        mDownloadLink = downloadLink;
    }

    public String getGenrer() {
        return mGenrer;
    }

    public void setGenrer(String genrer) {
        mGenrer = genrer;
    }
}
