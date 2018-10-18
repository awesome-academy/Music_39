package com.framgia.music_39.screen.listmusic;

import com.framgia.music_39.data.model.Song;
import java.util.List;

public interface ListMusicContract {
    interface View {
        void onSuccess(List<Song> songList);

        void onError(Exception e);
    }

    interface Presenter {
        void getListSongByGenres(String url);

        void setView(View view);
    }
}
