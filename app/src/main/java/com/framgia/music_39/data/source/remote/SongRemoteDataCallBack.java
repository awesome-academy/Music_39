package com.framgia.music_39.data.source.remote;

import com.framgia.music_39.data.model.Song;
import java.util.List;

public interface SongRemoteDataCallBack {
    void onDataSuccess(List<Song> songList);

    void onFail(Exception e);
}
