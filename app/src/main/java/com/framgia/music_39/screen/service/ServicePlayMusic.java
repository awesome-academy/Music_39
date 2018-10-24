package com.framgia.music_39.screen.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Song;
import java.io.IOException;
import java.util.List;

import static com.framgia.music_39.screen.utils.Constant.ONE;

public class ServicePlayMusic extends Service {

    private IBinder mIBinder = new ServicePlay();
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private List<Song> mSongList;
    private int mPosition;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class ServicePlay extends Binder {
        public ServicePlayMusic getService() {
            return ServicePlayMusic.this;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public void playMusic() {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mSongList.get(mPosition).getLink());
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Toast.makeText(getApplicationContext(), mSongList.get(mPosition).getNameSong() + "",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(int position, List<Song> songs) {
        mPosition = position;
        mSongList = songs;
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        playMusic();
    }

    public void next() {
        if (mPosition == mSongList.size() - ONE) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        playMusic();
    }

    public void pause() {
        mMediaPlayer.stop();
    }

    public void previous() {
        if (mPosition == 0) {
            mPosition = (mSongList.size() - ONE);
        } else {
            mPosition--;
        }
        playMusic();
    }

    public boolean checkPlay() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            return true;
        } else {
            mMediaPlayer.start();
            return false;
        }
    }

    public void downLoad() {
        DownloadManager downloadManager =
                (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        String linkDownLoad = mSongList.get(mPosition).getLink();
        if (linkDownLoad != null) {
            Uri uri = Uri.parse(linkDownLoad);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle(mSongList.get(mPosition).getNameSong());
            request.setDescription(R.string.downloading + "");
            assert downloadManager != null;
            downloadManager.enqueue(request);
        } else {
            Toast.makeText(this, R.string.null_link, Toast.LENGTH_SHORT).show();
        }
    }
}
