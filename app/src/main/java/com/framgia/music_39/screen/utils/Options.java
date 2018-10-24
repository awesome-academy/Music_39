package com.framgia.music_39.screen.utils;

import android.support.annotation.IntDef;

@IntDef({
        Options.NON_LOOP, Options.LOOP_ONE, Options.LOOP_ALL, Options.NON_SHUFFLE, Options.SHUFFLE
})
public @interface Options {
    int NON_LOOP = 0;
    int LOOP_ONE = 1;
    int LOOP_ALL = 2;
    int NON_SHUFFLE = 3;
    int SHUFFLE = 4;
}
