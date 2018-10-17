package com.framgia.music_39.screen.utils;

import android.support.annotation.StringDef;

@StringDef({
        Genres.MUSIC, Genres.AUDIO, Genres.ALTERNATIVE_ROCK, Genres.AMBIENT, Genres.CLASSICAL,
        Genres.COUNTRY
})
public @interface Genres {
    String MUSIC = "all music";
    String AUDIO = "audio";
    String ALTERNATIVE_ROCK = "alternative rock";
    String AMBIENT = "ambient";
    String CLASSICAL = "classical";
    String COUNTRY = "country";
}
