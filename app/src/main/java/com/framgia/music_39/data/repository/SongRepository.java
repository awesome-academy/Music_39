package com.framgia.music_39.data.repository;

import com.framgia.music_39.data.source.SongDataSource;
import com.framgia.music_39.data.source.remote.SongRemoteDataCallBack;

public class SongRepository {
    private static SongRepository mInstance;
    private SongDataSource.RemoteDataSource mRemoteDataSource;

    public SongRepository(SongDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static SongRepository getInstance(SongDataSource.RemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new SongRepository(remoteDataSource);
        }
        return mInstance;
    }

    public void getData(SongRemoteDataCallBack listener, String url) {
        mRemoteDataSource.getData(listener, url);
    }
}
