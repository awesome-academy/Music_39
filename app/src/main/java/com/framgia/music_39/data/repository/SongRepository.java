package com.framgia.music_39.data.repository;

import com.framgia.music_39.data.source.SongDataSource;
import com.framgia.music_39.data.source.local.SongLocalDataCallBack;
import com.framgia.music_39.data.source.remote.SongRemoteDataCallBack;

public class SongRepository {
    private static SongRepository mInstance;
    private SongDataSource.RemoteDataSource mRemoteDataSource;
    private SongDataSource.LocalDataSource mLocalDataSource;

    private SongRepository(SongDataSource.RemoteDataSource remoteDataSource,
            SongDataSource.LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static SongRepository getInstance(SongDataSource.RemoteDataSource remoteDataSource,
            SongDataSource.LocalDataSource localDataSource) {
        if (mInstance == null) {
            mInstance = new SongRepository(remoteDataSource, localDataSource);
        }
        return mInstance;
    }

    public void getListSongByGenres(String genre, SongRemoteDataCallBack listener) {
        mRemoteDataSource.getListSongByGenres(genre, listener);
    }

    public void getListSongLocal(SongLocalDataCallBack listener) {
        mLocalDataSource.getListSongLocal(listener);
    }
}
