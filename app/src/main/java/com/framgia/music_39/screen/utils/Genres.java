package com.framgia.music_39.screen.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

@StringDef({
        Genres.MUSIC, Genres.AUDIO, Genres.ALTERNATIVE_ROCK, Genres.AMBIENT, Genres.CLASSICAL,
        Genres.COUNTRY
})
@IntDef({
        Genres.GENRE_MUSIC, Genres.GENRE_AUDIO, Genres.GENRE_ALTERNATIVE_ROCK, Genres.GENRE_AMBIENT,
        Genres.GENRE_CLASSICAL, Genres.GENRE_COUNTRY
})
public @interface Genres {
    String MUSIC = "all music";
    String AUDIO = "audio";
    String ALTERNATIVE_ROCK = "alternative rock";
    String AMBIENT = "ambient";
    String CLASSICAL = "classical";
    String COUNTRY = "country";
    int GENRE_MUSIC = 0;
    int GENRE_AUDIO = 1;
    int GENRE_ALTERNATIVE_ROCK = 2;
    int GENRE_AMBIENT = 3;
    int GENRE_CLASSICAL = 4;
    int GENRE_COUNTRY = 5;
}
