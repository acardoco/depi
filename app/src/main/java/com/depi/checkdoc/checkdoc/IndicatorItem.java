package com.depi.checkdoc.checkdoc;

/**
 * Created by Carlos Clavero on 09/04/2017.
 */

public class IndicatorItem {
    private String indicator;
    private int state;



    public IndicatorItem(String indicator, int state){
        this.indicator = indicator;
        this.state = state;

    }

    public String getIndicator(){
        return indicator;
    }

    public int getState(){
        return state;
    }

}
