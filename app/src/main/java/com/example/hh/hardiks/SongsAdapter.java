package com.example.hh.hardiks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hh on 9/27/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {
    Context con;
    ArrayList<SongDetails> arr1;


    public SongsAdapter(Context con, ArrayList<SongDetails> arr1) {
        this.con = con;
        this.arr1 = arr1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.songName);
            img = (ImageView) view.findViewById(R.id.songImage);
        }
    }


    @Override
    public SongsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SongDetails son = arr1.get(position);
        holder.name.setText(son.getName());
        String s1 = son.getPath();


        //Bitmap bb= BitmapFactory.decodeFile(s1);
        //holder.img.setImageBitmap(bb);

        try {

            byte[] raw;
            Bitmap art;
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            BitmapFactory.Options bfo = new BitmapFactory.Options();
            mmr.setDataSource(con, Uri.parse(s1));
            raw = mmr.getEmbeddedPicture();
            if (raw != null) {
                art = BitmapFactory.decodeByteArray(raw, 0, raw.length, bfo);
                holder.img.setImageBitmap(art);
            } else
                holder.img.setImageResource(R.drawable.music);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Picasso.with(con).load(s1).centerCrop().resize(100,100).into(holder.img);

    }
        @Override
        public int getItemCount () {
            return arr1.size();
        }
}
