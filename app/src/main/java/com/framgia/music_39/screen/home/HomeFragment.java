package com.framgia.music_39.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Genre;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), getGenreList());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_genre);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeAdapter);
        return view;
    }

    private List<Genre> getGenreList() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(getString(R.string.music)));
        genreList.add(new Genre(getString(R.string.audio)));
        genreList.add(new Genre(getString(R.string.alternative_rock)));
        genreList.add(new Genre(getString(R.string.ambient)));
        genreList.add(new Genre(getString(R.string.classical)));
        genreList.add(new Genre(getString(R.string.country)));
        return genreList;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}
