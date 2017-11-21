package com.example.hh.hardiks;

/**
 * Created by hh on 9/27/2017.
 */

public class Playlist
{
    String id;
    String name;
    String data;
    String duration;
    String count;

    public Playlist(String id, String name, String data, String duration,String count)
    {
        this.id=id;
        this.data=data;
        this.duration=duration;
        this.name=name;
        this.count=count;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getDuration() {
        return duration;
    }
    public String getCount(){
        return count;
    }
}
