package com.framgia.music_39.data.source;

import com.framgia.music_39.data.source.remote.SongRemoteDataCallBack;

public interface SongDataSource {
    interface RemoteDataSource extends SongDataSource {
        void getData(SongRemoteDataCallBack listener, String url);
    }

    interface LocalDataSource extends SongDataSource {
        void getData();
    }
}
