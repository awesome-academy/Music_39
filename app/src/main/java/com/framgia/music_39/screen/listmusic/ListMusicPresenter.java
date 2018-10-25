package com.framgia.music_39.screen.listmusic;

import com.framgia.music_39.data.model.Song;
import com.framgia.music_39.data.repository.SongRepository;
import com.framgia.music_39.data.source.local.SongLocalDataCallBack;
import com.framgia.music_39.data.source.remote.SongRemoteDataCallBack;
import java.util.List;

public class ListMusicPresenter implements ListMusicContract.Presenter {
    private ListMusicContract.View mView;
    private SongRepository mSongRepository;

    public ListMusicPresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getListSongByGenres(String genre) {
        mSongRepository.getListSongByGenres(genre, new SongRemoteDataCallBack() {
            @Override
            public void onDataSuccess(List<Song> songList) {
                mView.onSuccess(songList);
            }

            @Override
            public void onFail(Exception e) {
                mView.onError(e);
            }
        });
    }

    @Override
    public void getListSongLocal() {
        mSongRepository.getListSongLocal(new SongLocalDataCallBack() {
            @Override
            public void onDataSuccess(List<Song> songList) {
                mView.onSuccess(songList);
            }

            @Override
            public void onFail(Exception e) {
                mView.onError(e);
            }
        });
    }

    @Override
    public void setView(ListMusicContract.View view) {
        mView = view;
    }
}
