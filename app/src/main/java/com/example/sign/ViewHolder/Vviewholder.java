package com.example.sign.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sign.Interface.ItemClickListener;
import com.example.sign.R;
import com.squareup.picasso.Picasso;

public class Vviewholder extends RecyclerView.ViewHolder  {


  public TextView vdiscount,vname,vtotal,vquantity;
  public ImageView vimage;

    public Vviewholder(@NonNull View itemView) {
        super(itemView);

    }


    public void setDetails(Context context,String discount, String image, String productName, String quantity, String price)
    {
        vdiscount=itemView.findViewById(R.id.view_discount);
        vname=itemView.findViewById(R.id.view_name);
        vtotal=itemView.findViewById(R.id.view_total);
        vimage=itemView.findViewById(R.id.view_image);
        vquantity=itemView.findViewById(R.id.view_quanity);

        vdiscount.setText(discount);
        vname.setText(productName);
        vquantity.setText(quantity);
        vtotal.setText(price);
        Picasso.get().load(image).into(vimage);

    }}

