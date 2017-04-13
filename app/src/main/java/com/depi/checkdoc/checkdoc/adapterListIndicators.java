package com.depi.checkdoc.checkdoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Carlos Clavero on 09/04/2017.
 */
class adapterListIndicators extends ArrayAdapter<IndicatorItem> {

    public adapterListIndicators(Context context, IndicatorItem[] data) {
            super(context, R.layout.list_item_indicators, data);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            ViewHolderIndicators holder;

            if(item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.list_item_indicators, null);
                holder = new ViewHolderIndicators();
                holder.indicator = (TextView)item.findViewById(R.id.LblIndicatorName);
                holder.state = (TextView)item.findViewById(R.id.LblStateIndicator);
                holder.imageState = (ImageView)item.findViewById(R.id.indicatorStateIm);

                item.setTag(holder);
            }

            else
            {
                holder = (ViewHolderIndicators)item.getTag();
            }

            holder.indicator.setText(((IndicatorItem)this.getItem(position)).getIndicator());

            switch(((IndicatorItem)this.getItem(position)).getState()){
                case 0:holder.state.setText("Estado:normal");
                       holder.imageState.setBackgroundResource(R.drawable.valornormal);break;
                case 1:holder.state.setText("Estado:valores bajos");
                    holder.imageState.setBackgroundResource(R.drawable.valormedio);break;
                case 2:holder.state.setText("Estado:crítico");
                    holder.imageState.setBackgroundResource(R.drawable.valorcritico);break;
            }


            return(item);
        }
}
