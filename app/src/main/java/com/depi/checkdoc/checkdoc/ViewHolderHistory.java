package com.depi.checkdoc.checkdoc;

import android.widget.TextView;

/**
 * Created by Carlos Clavero on 08/04/2017.
 */

public class ViewHolderHistory {
    TextView name;
    TextView item;
    TextView dateTime;


    public void setName(TextView name){
        this.name = name;
    }

    public void setItem(TextView item){
        this.item = item;
    }
    public void setDateTime(TextView dateTime){
        this.dateTime = dateTime;
    }



    public TextView getName(){
        return name;
    }

    public TextView getItem(){
        return item;
    }
    public TextView getDateTime(){
        return dateTime;
    }

}
