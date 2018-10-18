package com.framgia.music_39.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Genre;
import com.framgia.music_39.screen.listmusic.ListMusicFragment;
import com.framgia.music_39.screen.utils.Genres;
import com.framgia.music_39.screen.utils.ItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), getGenreList(), this);
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

    @Override
    public void onItemClicked(int position) {
        switch (position) {
            case Genres.GENRE_1:
                addFragment(ListMusicFragment.getListMusicFragment(Genres.MUSIC));
                break;
            case Genres.GENRE_2:
                addFragment(ListMusicFragment.getListMusicFragment(Genres.AUDIO));
                break;
            case Genres.GENRE_3:
                addFragment(ListMusicFragment.getListMusicFragment(Genres.ALTERNATIVE_ROCK));
                break;
            case Genres.GENRE_4:
                addFragment(ListMusicFragment.getListMusicFragment(Genres.AMBIENT));
                break;
            case Genres.GENRE_5:
                addFragment(ListMusicFragment.getListMusicFragment(Genres.CLASSICAL));
                break;
            case Genres.GENRE_6:
                addFragment(ListMusicFragment.getListMusicFragment(Genres.COUNTRY));
                break;
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_content_list_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
