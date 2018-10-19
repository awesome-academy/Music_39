package com.framgia.music_39.screen.utils;

import com.framgia.music_39.BuildConfig;

public class Constant {
    private static final String HTTP_API = "https://api.soundcloud.com/tracks";
    private static final String CLIENT_ID = "?client_id=" + BuildConfig.API_KEY;
    private static final String LINKED_PARTITION = "&linked_partitioning=1&limit=10";
    private static final String GENRES = "&genres=";
    public static final String GENRES_URL = HTTP_API + CLIENT_ID + LINKED_PARTITION + GENRES;
}
