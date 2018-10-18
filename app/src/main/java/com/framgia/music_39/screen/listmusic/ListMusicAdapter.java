package com.framgia.music_39.screen.listmusic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Song;
import com.framgia.music_39.screen.utils.ItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ViewHolder> {
    private Context mContext;
    private List<Song> mSongList;
    private ItemClickListener mItemClickListener;

    ListMusicAdapter(Context context) {
        mContext = context;
        mSongList = new ArrayList<>();
    }

    public void updateData(List<Song> list) {
        if (mSongList != null) {
            mSongList.clear();
        }
        assert list != null;
        mSongList = list;
        notifyDataSetChanged();
    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mSongList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongList != null ? mSongList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewSong;
        private TextView mTextViewNameSong;
        private TextView mTextViewNameArtist;

        ViewHolder(View itemView) {
            super(itemView);
            mImageViewSong = itemView.findViewById(R.id.imageView_song_item);
            mTextViewNameSong = itemView.findViewById(R.id.textView_name_song_item);
            mTextViewNameArtist = itemView.findViewById(R.id.textView_name_artist_item);
        }

        void bindData(Song song) {
            mTextViewNameSong.setText(song.getNameSong());
            mTextViewNameArtist.setText(song.getNameArtist());
            Glide.with(mContext)
                    .load(song.getImageSong())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_file_mp3))
                    .into(mImageViewSong);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClicked(getAdapterPosition());
            }
        }
    }
}
