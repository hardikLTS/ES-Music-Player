package com.example.hh.hardiks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayMusic extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,MediaPlayer.OnCompletionListener {
    ImageView i1, i2;
    TextView t1, t2, t3;
    SeekBar see;
    ImageButton ib1, ib2, ib3,ib4,ib5,ib6,ib7;
    Handler handler;
    MediaPlayer mp = null;
    Intent intent;
    int flag = 0;
    int repeat,shuffle;
    int k;
    Runnable r;

    String sonname,sonpath,dur,arti;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playmusic);

        repeat=0;
        shuffle=0;
        i2 = (ImageView) findViewById(R.id.artimg);
        see = (SeekBar) findViewById(R.id.seekBar);

        handler = new Handler();
        mp = new MediaPlayer();

        see.setOnSeekBarChangeListener(this);
        mp.setOnCompletionListener(this);

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);

        ib1 = (ImageButton) findViewById(R.id.iswitch);//song image
        ib2 = (ImageButton) findViewById(R.id.iswitch1);//play image
        ib3 = (ImageButton) findViewById(R.id.iswitch2);//fast forward song

        ib4=(ImageButton) findViewById(R.id.ib4);//next song
        ib5=(ImageButton) findViewById(R.id.ib5);//backward song

        ib6=(ImageButton) findViewById(R.id.repeat);//repeat song

        ib7=(ImageButton) findViewById(R.id.shuf);//shuffle song

        intent = getIntent();
         k = intent.getIntExtra("value", 0);

        SongDetails son = MainActivity.arr.get(k);

        String arti = son.getArtistName();

        String sonname = son.getName();

        String sonpath = son.getPath();

        String dur = son.getDuration();

        try {
                byte[] raw;
                Bitmap art;
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                BitmapFactory.Options bfo = new BitmapFactory.Options();
                mmr.setDataSource(this, Uri.parse(sonpath));
                raw = mmr.getEmbeddedPicture();
                if (raw != null) {
                    art = BitmapFactory.decodeByteArray(raw, 0, raw.length, bfo);
                    i2.setImageBitmap(art);
                } else {
                    i2.setImageResource(R.drawable.music);
                }

                if(mp!=null && mp.isPlaying())
                {
                    mp.stop();
                    mp.release();
                    mp=null;
                }
                else
                {
                    mp.setDataSource(sonpath);
                    mp.prepare();
                    mp.start();
                    see.setProgress(0);
                    see.setMax(mp.getDuration());
                     r=new Runnable()
                     {
                        @Override
                        public void run()
                        {
                            int total=mp.getDuration();
                            int curr=mp.getCurrentPosition();

                            int prog=(int)getProgressPercentage(curr,total);
                            see.setProgress(prog);

                            handler.postDelayed(this,100);//calls runnable interface
                        }
                    };
                    handler.postDelayed(r,100);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread t=new MyThread();
        t.start();
        t1.setText(sonname);

        t2.setText(arti);

        Toast.makeText(this, sonname + "::" + arti + "::" + dur, Toast.LENGTH_LONG).show();

        ib1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(flag==0)
                {
                    flag=1;
                    ib1.setImageResource(R.drawable.play);
                    mp.pause();
                }
                else
                {
                    flag=0;
                    ib1.setImageResource(R.drawable.pause);
                    mp.start();
                }
            }
        });
        ib2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int cur=mp.getCurrentPosition();
                int durat=mp.getDuration();

                if(durat-cur>=4)
                    mp.seekTo(cur+4000);
                else
                    mp.seekTo(cur+1000);
            }
        });
        ib3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int cur=mp.getCurrentPosition();
                int durat=mp.getDuration();

                if(durat-4000>=0)
                    mp.seekTo(cur-4000);
                else
                    mp.seekTo(0);
            }
        });

        //Play next song
        ib4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Intent ii=new Intent(getApplicationContext(),PlayMusic.class);
                if(k<MainActivity.arr.size()-1)
                {
                    //    ii.putExtra("value",k+1);
                    k=k+1;
                }
                else
                    k=0;
                SongDetails sonNext=MainActivity.arr.get(k);
                String sonpath=sonNext.getPath();

                t1.setText(sonNext.getName());
                t2.setText(sonNext.getArtistName());

                try {
                    byte[] raw;
                    Bitmap art;
                    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                    BitmapFactory.Options bfo = new BitmapFactory.Options();
                    mmr.setDataSource(getApplicationContext(), Uri.parse(sonpath));
                    raw = mmr.getEmbeddedPicture();
                    if (raw != null) {
                        art = BitmapFactory.decodeByteArray(raw, 0, raw.length, bfo);
                        i2.setImageBitmap(art);
                    } else {
                        i2.setImageResource(R.drawable.music);
                    }
                    mp.stop();
                    try{
                        mp.setDataSource(sonpath);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        //Play back song
        ib5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent ii=new Intent(getApplicationContext(),PlayMusic.class);
                if(k>0)
                {
                    ii.putExtra("value",k-1);
                }
                else
                {
                    ii.putExtra("value",MainActivity.arr.size()-1);
                }
                startActivity(ii);
            }
        });
        ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat=1;
            }
        });

        ib7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffle=1;
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {
        Intent ii=new Intent(this,PlayMusic.class);
        if(repeat==1)
        {
            ii.putExtra("value",k);
            startActivity(ii);
        }
        if(shuffle==1)
        {

            k=(int)Math.random()*(MainActivity.arr.size()-1);
            k+=0;
            ii.putExtra("value",k);
            startActivity(ii);
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        handler.removeCallbacks(r);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        handler.removeCallbacks(r);
        int tot=mp.getDuration();
        int curr=progressToTimer(seekBar.getProgress(),tot);
        mp.seekTo(curr);
        handler.postDelayed(r,100);
    }

    public int getProgressPercentage(long currentDuration, long totalDuration){
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage =(((double)currentSeconds)/totalSeconds)*100;

        // return percentage
        return percentage.intValue();
    }

    public int progressToTimer(int progress, int totalDuration)
    {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }
    public class MyThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                if (mp != null) {
                    see.setProgress(mp.getCurrentPosition());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
