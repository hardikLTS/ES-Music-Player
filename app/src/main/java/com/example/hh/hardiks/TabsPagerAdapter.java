package com.example.hh.hardiks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
 * Created by hh on 9/27/2017.
*/


public class TabsPagerAdapter extends FragmentPagerAdapter
{
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
              @Override
              public Fragment getItem(int position)
          {
              switch(position){
                  case 0:
                      return new SongsDisplay();
                  case 1:
                    return new AlbumDisplay();
      }
      return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
