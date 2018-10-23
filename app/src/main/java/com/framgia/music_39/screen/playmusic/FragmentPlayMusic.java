package com.framgia.music_39.screen.playmusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Song;
import com.framgia.music_39.screen.service.ServicePlayMusic;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.framgia.music_39.screen.utils.Constant.ONE;

public class FragmentPlayMusic extends Fragment
        implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    private static final String BUNDLE_POSITION_SONG = "BUNDLE_POSITION_SONG";
    private static final String BUNDLE_LIST_SONG = "BUNDLE_LIST_SONG";

    private ImageButton mButtonPlay;
    private TextView mTextViewNameSong;
    private TextView mTextViewNameArtist;
    private TextView mTextViewTimeCurrent;
    private TextView mTextViewTimeFinal;
    private CircleImageView mImageMusic;
    private SeekBar mSeekBar;

    private TextView mTextViewNameSongMini;
    private TextView mTextViewNameArtistMini;
    private ImageButton mButtonPlayMini;
    private CircleImageView mImageMusicMini;
    private List<Song> mSongList;
    private int mPosition;

    private static ServicePlayMusic mServicePlayMusic;
    private MediaPlayer mMediaPlayer;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;
    private Animation mRotateAnimation;
    private boolean mIsBoundService;
    private View mView;
    private View mViewListMusic;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServicePlayMusic.ServicePlay binder = (ServicePlayMusic.ServicePlay) service;
            mServicePlayMusic = binder.getService();
            mIsBoundService = true;
            setServiceConnection();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBoundService = false;
        }
    };

    public static FragmentPlayMusic newInstance(int position, ArrayList<Song> songList) {
        FragmentPlayMusic fragmentPlayMusic = new FragmentPlayMusic();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_POSITION_SONG, position);
        args.putParcelableArrayList(BUNDLE_LIST_SONG, songList);
        fragmentPlayMusic.setArguments(args);
        return fragmentPlayMusic;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_play_music, container, false);
        mViewListMusic = inflater.inflate(R.layout.activity_main, container, false);
        initView();
        initRunnable();
        initData();
        setUISongMini();
        Intent intent = new Intent(this.getActivity(), ServicePlayMusic.class);
        this.getActivity().bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        return mView;
    }

    private void initRunnable() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
                mTextViewTimeCurrent.setText(
                        parseDurationToStringTime(mMediaPlayer.getCurrentPosition()));
                mHandler.postDelayed(this, 0);
            }
        };
    }

    private void initView() {
        ImageButton buttonBack = mView.findViewById(R.id.buttonBack);
        ImageButton buttonDownload = mView.findViewById(R.id.buttonDownload);
        ImageButton buttonNext = mView.findViewById(R.id.buttonNext);
        ImageButton buttonPrevious = mView.findViewById(R.id.buttonPrevious);
        mButtonPlay = mView.findViewById(R.id.buttonPlay);

        mView.findViewById(R.id.layout_bottom).setVisibility(View.GONE);
        mViewListMusic.findViewById(R.id.layout_home).setVisibility(View.GONE);

        mTextViewNameSong = mView.findViewById(R.id.textViewNameSong);
        mTextViewNameArtist = mView.findViewById(R.id.textViewNameArtist);
        mTextViewTimeCurrent = mView.findViewById(R.id.textViewTimeCurrent);
        mTextViewTimeFinal = mView.findViewById(R.id.textViewTimeFinal);

        mImageMusic = mView.findViewById(R.id.imageCD);
        mSeekBar = mView.findViewById(R.id.seekBar);

        mTextViewNameSongMini = mView.findViewById(R.id.textViewNameSongMini);
        mTextViewNameArtistMini = mView.findViewById(R.id.textViewNameArtistMini);
        ImageButton buttonNextMini = mView.findViewById(R.id.buttonNextMini);
        ImageButton buttonPreviousMini = mView.findViewById(R.id.buttonPreviousMiNi);
        mButtonPlayMini = mView.findViewById(R.id.buttonPlayMini);
        mImageMusicMini = mView.findViewById(R.id.imageDiscMini);

        buttonPreviousMini.setOnClickListener(this);
        mButtonPlayMini.setOnClickListener(this);
        buttonNextMini.setOnClickListener(this);

        buttonBack.setOnClickListener(this);
        buttonDownload.setOnClickListener(this);
        buttonPrevious.setOnClickListener(this);
        mButtonPlay.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

    private void setServiceConnection() {
        if (mIsBoundService) {
            mServicePlayMusic.play(mPosition, mSongList);
            setUISong();
            mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        }
        mHandler.postDelayed(mRunnable, 0);
        mIsBoundService = true;
    }

    public void initData() {
        mSongList = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mSongList = bundle.getParcelableArrayList(BUNDLE_LIST_SONG);
            mPosition = bundle.getInt(BUNDLE_POSITION_SONG);
        }
    }

    public void setUISong() {
        mMediaPlayer = mServicePlayMusic.getMediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        Glide.with(this)
                .load(mSongList.get(mPosition).getImageSong())
                .apply(new RequestOptions().placeholder(R.drawable.ic_disc1))
                .into(mImageMusic);
        mTextViewNameSong.setText(mSongList.get(mPosition).getNameSong());
        mTextViewNameArtist.setText(mSongList.get(mPosition).getNameArtist());
        mTextViewTimeFinal.setText(parseDurationToStringTime(mMediaPlayer.getDuration()));
        setSeekBar();
        mRotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anim);
        mImageMusic.startAnimation(mRotateAnimation);
    }

    public void setSeekBar() {
        mSeekBar.setMax(mMediaPlayer.getDuration());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input) {
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void setUISongMini() {
        mTextViewNameSongMini.setText(mSongList.get(mPosition).getNameSong());
        mTextViewNameArtistMini.setText(mSongList.get(mPosition).getNameArtist());
        Glide.with(getActivity())
                .load(mSongList.get(mPosition).getImageSong())
                .apply(new RequestOptions().placeholder(R.drawable.ic_disc1))
                .into(mImageMusicMini);
    }

    private String parseDurationToStringTime(long duration) {
        return String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    public void setVisibilityView() {
        mView.findViewById(R.id.layout_top).setVisibility(View.GONE);
        mView.findViewById(R.id.layout_bottom).setVisibility(View.VISIBLE);
        mViewListMusic.findViewById(R.id.layout_home).setVisibility(View.GONE);
    }

    public void setButtonNext() {
        mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        mButtonPlayMini.setImageResource(R.drawable.ic_pause_black_24dp);
        if (mPosition == mSongList.size() - ONE) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        mServicePlayMusic.next();
        setUISong();
        setUISongMini();
    }

    public void setButtonPlay() {
        if (mServicePlayMusic.checkPlay()) {
            mButtonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            mButtonPlayMini.setImageResource(R.drawable.ic_play_arrow_white_24dp);
            mRotateAnimation.setRepeatCount(0);
            mImageMusic.clearAnimation();
        } else {
            mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
            mButtonPlayMini.setImageResource(R.drawable.ic_pause_black_24dp);
            mRotateAnimation.setRepeatCount(Animation.INFINITE);
            mImageMusic.startAnimation(mRotateAnimation);
        }
    }

    public void setButtonPrevious() {
        mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        mButtonPlayMini.setImageResource(R.drawable.ic_pause_black_24dp);
        if (mPosition == 0) {
            mPosition = mSongList.size() - ONE;
        } else {
            mPosition--;
        }
        mServicePlayMusic.previous();
        setUISongMini();
        setUISong();
    }

    public void setButtonNextMini() {
        mButtonPlayMini.setImageResource(R.drawable.ic_pause_black_24dp);
        mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        if (mPosition == mSongList.size() - ONE) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        mServicePlayMusic.next();
        setUISongMini();
        setUISong();
    }

    public void setButtonPlayMini() {
        if (mServicePlayMusic.checkPlay()) {
            mButtonPlayMini.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            mButtonPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
            mRotateAnimation.setRepeatCount(0);
            mImageMusic.clearAnimation();
        } else {
            mButtonPlayMini.setImageResource(R.drawable.ic_pause_black_24dp);
            mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
            mRotateAnimation.setRepeatCount(Animation.INFINITE);
            mImageMusic.getRotation();
            mImageMusic.startAnimation(mRotateAnimation);
        }
    }

    public void setButtonPreviousMini() {
        mButtonPlayMini.setImageResource(R.drawable.ic_pause_black_24dp);
        mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        if (mPosition == 0) {
            mPosition = mSongList.size() - ONE;
        } else {
            mPosition--;
        }
        mServicePlayMusic.previous();
        setUISongMini();
        setUISong();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                setVisibilityView();
                break;
            case R.id.buttonNext:
                setButtonNext();
                break;
            case R.id.buttonPlay:
                setButtonPlay();
                break;
            case R.id.buttonPrevious:
                setButtonPrevious();
                break;
            case R.id.buttonNextMini:
                setButtonNextMini();
                break;
            case R.id.buttonPlayMini:
                setButtonPlayMini();
                break;
            case R.id.buttonPreviousMiNi:
                setButtonPreviousMini();
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mButtonPlayMini.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        mButtonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        mRotateAnimation.setRepeatCount(0);
        mImageMusic.clearAnimation();
    }
}
