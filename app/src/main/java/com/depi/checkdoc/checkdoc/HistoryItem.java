package com.depi.checkdoc.checkdoc;

/**
 * Created by Carlos Clavero on 08/04/2017.
 */

public class HistoryItem {
    private String name;
    private String item;
    private String date;
    private String time;


    public HistoryItem(String name, String item,String date, String time){
        this.name = name;
        this.item = item;
        this.date = date;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public String getItem(){
        return item;
    }
    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }
}
