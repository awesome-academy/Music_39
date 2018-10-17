package com.framgia.music_39.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String mNameSong;
    private String mNameArtist;
    private String mImageSong;
    private String mLink;
    private String mDuration;
    private String mDownloadLink;

    public Song(SongBuilder songBuilder) {
        mNameSong = songBuilder.mNameSong;
        mNameArtist = songBuilder.mNameArtist;
        mImageSong = songBuilder.mImageSong;
        mLink = songBuilder.mLink;
        mDuration = songBuilder.mDuration;
        mDownloadLink = songBuilder.mDownloadLink;
    }

    public Song() {
    }

    protected Song(Parcel in) {
        mNameSong = in.readString();
        mNameArtist = in.readString();
        mImageSong = in.readString();
        mLink = in.readString();
        mDuration = in.readString();
        mDownloadLink = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mNameSong);
        dest.writeString(mNameArtist);
        dest.writeString(mImageSong);
        dest.writeString(mLink);
        dest.writeString(mDuration);
        dest.writeString(mDownloadLink);
    }

    public static class SongBuilder {
        private String mNameSong;
        private String mNameArtist;
        private String mImageSong;
        private String mLink;
        private String mDuration;
        private String mDownloadLink;

        public SongBuilder(String nameSong, String nameArtist, String imageSong, String link,
                String duration, String downloadLink) {
            mNameSong = nameSong;
            mNameArtist = nameArtist;
            mImageSong = imageSong;
            mLink = link;
            mDuration = duration;
            mDownloadLink = downloadLink;
        }

        public SongBuilder() {
        }

        public SongBuilder nameSong(String nameSong) {
            mNameSong = nameSong;
            return this;
        }

        public SongBuilder nameArtist(String nameArtist) {
            mNameArtist = nameArtist;
            return this;
        }

        public SongBuilder imageSong(String imageSong) {
            mImageSong = imageSong;
            return this;
        }

        public SongBuilder linkSong(String link) {
            mLink = link;
            return this;
        }

        public SongBuilder duration(String duration) {
            mDuration = duration;
            return this;
        }

        public SongBuilder downloadLink(String downloadLink) {
            mDownloadLink = downloadLink;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
    }

    public final class SongEntry {
        public static final String NAME_SONG = "title";
        public static final String NAME_ARTIST = "label_name";
        public static final String URL_IMAGE = "artwork_url";
        public static final String URL_PERMALINK = "permalink_url";
        public static final String DURATION = "duration";
        public static final String URL_DOWNLOAD = "download_url";
    }
}
