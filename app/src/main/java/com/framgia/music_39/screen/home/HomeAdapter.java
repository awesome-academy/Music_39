package com.framgia.music_39.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Genre;
import com.framgia.music_39.screen.utils.ItemClickListener;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context mContext;
    private List<Genre> mGenreList;
    private ItemClickListener mClickListener;

    HomeAdapter(Context context, List<Genre> genreList, ItemClickListener clickListener) {
        mContext = context;
        mGenreList = genreList;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(itemView, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mGenreList.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenreList != null ? mGenreList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mGenreImageView;
        private ItemClickListener mClickListener;

        ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            mClickListener = itemClickListener;
            mGenreImageView = itemView.findViewById(R.id.imageView_item);
            itemView.setOnClickListener(this);
        }

        private void bindData(Genre genre) {
            Glide.with(mContext).load(genre.getGenreImage()).into(mGenreImageView);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClicked(getAdapterPosition());
            }
        }
    }
}
