package com.example.hh.hardiks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hh on 9/28/2017.
 */

public class AlbumSongsAdapter extends RecyclerView.Adapter<AlbumSongsAdapter.MyViewHolder>
{
    Context con;
    ArrayList<SongDetails>arry;
    public AlbumSongsAdapter(Context con,ArrayList<SongDetails>arry)
    {
        this.con=con;
        this.arry=arry;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public MyViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    @Override
    public AlbumSongsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AlbumSongsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
