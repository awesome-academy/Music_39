package com.framgia.music_39.data.source.local;

import com.framgia.music_39.data.model.Song;
import java.util.List;

public interface SongLocalDataCallBack {
    void onDataSuccess(List<Song> songList);

    void onFail(Exception e);
}
