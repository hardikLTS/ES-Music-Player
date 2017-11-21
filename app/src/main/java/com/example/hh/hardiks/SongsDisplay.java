package com.example.hh.hardiks;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class SongsDisplay extends android.support.v4.app.Fragment
{
    RecyclerView rec;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.activity_data_songs_display, container, false);

        rec=(RecyclerView)rootView.findViewById(R.id.recy);

        rec.setAdapter(new SongsAdapter(getActivity(),MainActivity.arr));

        rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        rec.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        rec.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getContext(),"position::"+position,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity().getApplicationContext(),PlayMusic.class);
                i.putExtra("value",position);
                startActivity(i);
            }
        }));

        return rootView;
    }
}
