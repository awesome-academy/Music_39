package com.framgia.music_39.data.source;

import com.framgia.music_39.data.source.remote.SongRemoteDataCallBack;

public interface SongDataSource {
    interface RemoteDataSource extends SongDataSource {
        void getListSongByGenres(String genre, SongRemoteDataCallBack listener);
    }

    interface LocalDataSource extends SongDataSource {
        void getData();
    }
}
