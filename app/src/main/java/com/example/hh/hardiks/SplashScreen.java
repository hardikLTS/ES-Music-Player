package com.example.hh.hardiks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity
{
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //pb=(ProgressBar)findViewById(R.id.progressBar);

        Thread time=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };
        time.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
