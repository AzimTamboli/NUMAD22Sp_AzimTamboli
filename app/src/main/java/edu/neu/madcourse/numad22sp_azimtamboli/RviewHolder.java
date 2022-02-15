package edu.neu.madcourse.numad22sp_azimtamboli;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/*
    I have borrowed this code from professors sample code which I found extremely helpful.
     */

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemUrl;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        itemName = itemView.findViewById(R.id.name);
        itemUrl = itemView.findViewById(R.id.url);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });

    }
}