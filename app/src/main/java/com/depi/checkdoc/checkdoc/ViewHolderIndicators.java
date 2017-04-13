package com.depi.checkdoc.checkdoc;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Carlos Clavero on 09/04/2017.
 */

public class ViewHolderIndicators {
    TextView indicator;
    TextView state;
    ImageView imageState;


    public void setIndicator(TextView indicator){
        this.indicator = indicator;
    }

    public void setState(TextView state){
        this.state = state;
    }
    public void setImageState(ImageView imageState){
        this.imageState = imageState;
    }



    public TextView getIndicator(){
        return indicator;
    }

    public TextView getState(){
        return state;
    }
    public ImageView getDateTime(){
        return imageState;
    }

}

