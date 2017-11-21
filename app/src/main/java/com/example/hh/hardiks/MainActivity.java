package com.example.hh.hardiks;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener
{
    static ArrayList<SongDetails>arr;
    static ArrayList<Playlist>arr2;
    public ViewPager viewPager;
    public TabsPagerAdapter mAdapter;
    public android.app.ActionBar actionBar;

    public String[] tabs = new String[]{"Songs", "Albums"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSongData();
        //getPlaylistData();
        Intent ii=getIntent();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Adding tabs

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));

        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public void getSongData()
    {
        arr=new ArrayList<>();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String s1= MediaStore.Audio.Media.IS_MUSIC+" !=0";

        String[]projection=new String[]
                {
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.DURATION,
                };
        Cursor cur = this.managedQuery(musicUri, projection,s1,null, null);

        cur.moveToFirst();

        while (cur.moveToNext()) {
            SongDetails song = new SongDetails(cur.getString(0), cur.getString(1), cur.getString(2), cur.getString(3), cur.getString(4), cur.getString(5));
            arr.add(song);
        }
        Toast.makeText(this, "size is::::" + arr.size(), Toast.LENGTH_SHORT).show();
    }

    /*public void getPlaylistData()
    {
        Uri musicUri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;

        String[]projection=new String[]
                {
                        MediaStore.Audio.Playlists._ID,
                        MediaStore.Audio.Playlists.NAME,
                        MediaStore.Audio.Playlists.DATA,
                        MediaStore.Audio.Playlists._COUNT
                };
    }*/
}
