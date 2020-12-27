package com.example.sign.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sign.Interface.ItemClickListener;
import com.example.sign.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public  TextView Utitle,Udesc,Uprice;
public ImageView image2;
private ItemClickListener itemClickListener;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        Utitle=itemView.findViewById(R.id.user_title);
        Udesc=itemView.findViewById(R.id.user_desc);
        image2=itemView.findViewById(R.id.user_image);
        Uprice=itemView.findViewById(R.id.user_price);

        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);


    }
}
