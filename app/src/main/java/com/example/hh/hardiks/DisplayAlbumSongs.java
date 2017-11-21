package com.example.hh.hardiks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayAlbumSongs extends AppCompatActivity
{
    RecyclerView recy;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_album_songs);

        recy=(RecyclerView)findViewById(R.id.recyclerViewOfAlbumSongs);

        Intent i=getIntent();
        String pos=i.getStringExtra("value");

        ArrayList<SongDetails>arry=new ArrayList<>();

        int len=MainActivity.arr.size();

        for(int j=0;j<len;++j)
        {
            if(MainActivity.arr.get(j).albumName.equalsIgnoreCase(pos))
                arry.add(MainActivity.arr.get(j));
        }
        recy.setAdapter(new AlbumSongsAdapter(this,arry));
        recy.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


    }
}
