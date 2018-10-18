package com.framgia.music_39.data.source.remote;

import com.framgia.music_39.data.source.SongDataSource;
import com.framgia.music_39.screen.utils.Constant;

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
    public void getListSongByGenres(String genre, SongRemoteDataCallBack listener) {
        FetchData fetchData = new FetchData(listener);
        fetchData.execute(Constant.GENRES_URL + genre);
    }
}
