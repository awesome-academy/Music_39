package com.framgia.music_39.data.source.remote;

import com.framgia.music_39.data.source.SongDataSource;

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {
    private static SongRemoteDataSource mInstance;

    private SongRemoteDataSource() {
    }

    public static SongRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new SongRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public void getData(SongRemoteDataCallBack listener, String url) {
        FetchData fetchData = new FetchData(listener);
        fetchData.execute(url);
    }
}
