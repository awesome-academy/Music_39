package com.framgia.music_39.screen.home;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.framgia.music_39.R;
import com.framgia.music_39.data.model.Genre;
import com.framgia.music_39.screen.utils.Genres;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context mContext;
    private List<Genre> mGenreList;

    HomeAdapter(Context context, List<Genre> genreList) {
        mContext = context;
        mGenreList = genreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(itemView);
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
        private static final String DRAWABLE = "drawable";
        private ImageView mGenreImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mGenreImageView = itemView.findViewById(R.id.imageView_item);
            itemView.setOnClickListener(this);
        }

        private void bindData(Genre genre) {
            mGenreImageView.setImageResource(mContext.getResources()
                    .getIdentifier(genre.getGenreImage(), DRAWABLE, mContext.getPackageName()));
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
