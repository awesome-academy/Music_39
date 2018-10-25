package com.framgia.music_39.data.source.local;

import android.content.ContentResolver;
import com.framgia.music_39.data.source.SongDataSource;

public class SongLocalDataSource implements SongDataSource.LocalDataSource {
    private static SongLocalDataSource mInstance;
    private static ContentResolver mResolver;

    private SongLocalDataSource(ContentResolver resolver) {
        mResolver = resolver;
    }

    public static SongLocalDataSource getInstance(ContentResolver resolver) {
        if (mInstance == null) {
            mInstance = new SongLocalDataSource(resolver);
        }
        return mInstance;
    }

    @Override
    public void getListSongLocal(SongLocalDataCallBack listener) {
        LoadAllSongLocal loadAllSongLocal = new LoadAllSongLocal(mResolver);
        listener.onDataSuccess(loadAllSongLocal.getSongList());
    }
}
