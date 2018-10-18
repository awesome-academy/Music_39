package com.framgia.music_39.screen.listmusic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Song;
import com.framgia.music_39.data.repository.SongRepository;
import com.framgia.music_39.data.source.remote.SongRemoteDataSource;
import com.framgia.music_39.screen.utils.ItemClickListener;
import java.util.List;

public class ListMusicFragment extends Fragment
        implements ListMusicContract.View, ItemClickListener {

    private static String ARGUMENT_GENRE = "ARGUMENT_GENRE";

    private ListMusicAdapter mListMusicAdapter;

    public static ListMusicFragment getListMusicFragment(String genre) {
        ListMusicFragment listMusicFragment = new ListMusicFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_GENRE, genre);
        listMusicFragment.setArguments(args);
        return listMusicFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_music, container, false);
        initView(view);
        initPresenter();
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_music);
        recyclerView.setHasFixedSize(true);
        mListMusicAdapter = new ListMusicAdapter(getContext());
        mListMusicAdapter.setItemClickListener(this);
        recyclerView.setAdapter(mListMusicAdapter);
    }

    private void initPresenter() {
        SongRemoteDataSource songRemoteDataSource = SongRemoteDataSource.getInstance();
        SongRepository songRepository = SongRepository.getInstance(songRemoteDataSource);
        assert getArguments() != null;
        String genre = getArguments().getString(ARGUMENT_GENRE);
        ListMusicPresenter listMusicPresenter = new ListMusicPresenter(songRepository);
        listMusicPresenter.setView(this);
        listMusicPresenter.getListSongByGenres(genre);
    }

    @Override
    public void onSuccess(List<Song> songList) {
        assert songList != null;
        mListMusicAdapter.updateData(songList);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position) {

    }
}
