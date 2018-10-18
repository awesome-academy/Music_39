package com.framgia.music_39.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_39.data.model.Song;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchData extends AsyncTask<String, Void, List<Song>> {
    private static final String GET = "GET";
    private static final String COLLECTION = "collection";

    private SongRemoteDataCallBack mSongRemoteDataCallBack;
    private List<Song> mSongList = new ArrayList<>();
    private String mData = "";

    FetchData(SongRemoteDataCallBack songRemoteDataCallBack) {
        mSongRemoteDataCallBack = songRemoteDataCallBack;
    }

    @Override
    protected List<Song> doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(GET);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                mData = mData + line;
            }
            httpURLConnection.disconnect();
            mSongList = pareJsonToObject(mData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mSongRemoteDataCallBack.onFail(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mSongList;
    }

    private List<Song> pareJsonToObject(String data) {
        List<Song> songList = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(data);
            JSONArray jsonArray = jsonObj.getJSONArray(COLLECTION);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Song song = new Song.SongBuilder().nameSong(
                        jsonObject.getString(Song.SongEntry.NAME_SONG))
                        .nameArtist(jsonObject.getString(Song.SongEntry.NAME_ARTIST))
                        .imageSong(jsonObject.getString(Song.SongEntry.URL_IMAGE))
                        .linkSong(jsonObject.getString(Song.SongEntry.URL_PERMALINK))
                        .duration(jsonObject.getString(Song.SongEntry.DURATION))
                        .downloadLink(jsonObject.getString(Song.SongEntry.URL_DOWNLOAD))
                        .build();
                songList.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return songList;
    }

    @Override
    protected void onPostExecute(List<Song> songList) {
        super.onPostExecute(songList);
        mSongRemoteDataCallBack.onDataSuccess(songList);
    }
}
