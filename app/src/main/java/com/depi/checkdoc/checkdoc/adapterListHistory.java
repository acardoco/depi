package com.depi.checkdoc.checkdoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Carlos Clavero on 08/04/2017.
 */
class adapterListHistory extends ArrayAdapter<HistoryItem> {

    public adapterListHistory(Context context, HistoryItem[] data) {
        super(context, R.layout.list_item_history, data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ViewHolderHistory holder;

        if(item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.list_item_history, null);
            holder = new ViewHolderHistory();
            holder.name = (TextView)item.findViewById(R.id.LblHistoryName);
            holder.item = (TextView)item.findViewById(R.id.LblHistoryItem);
            holder.dateTime = (TextView)item.findViewById(R.id.DateTime);

            item.setTag(holder);
        }

        else
        {
            holder = (ViewHolderHistory)item.getTag();
        }

        holder.name.setText(((HistoryItem)this.getItem(position)).getName());
        holder.item.setText(((HistoryItem)this.getItem(position)).getItem());
        holder.dateTime.setText(((HistoryItem)this.getItem(position)).getDate()+" "+((HistoryItem)this.getItem(position)).getTime());

        return(item);
    }
}
