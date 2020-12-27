package com.example.sign.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sign.Interface.ItemClickListener;
import com.example.sign.R;

public class MenuViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
   public AppCompatTextView textView;
   public ImageView imageView;
   private ItemClickListener itemClickListener;

    public MenuViewholder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.menu_title);
        imageView=itemView.findViewById(R.id.menu_shelf);

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
