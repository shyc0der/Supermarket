package com.example.sign.ViewHolder;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.sign.Interface.ItemClickListener;
import com.example.sign.Model.Order;
import com.example.sign.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
class cartViewholder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView cprice,cTitle;
    public ImageView cimage,cimage2;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setcTitle(TextView cTitle) {
        this.cTitle = cTitle;
    }

    public cartViewholder(@NonNull View itemView) {
        super(itemView);
        cTitle=itemView.findViewById(R.id.pd_title);
        cprice=itemView.findViewById(R.id.pdprice);
        cimage=itemView.findViewById(R.id.pdimage);
        cimage2=itemView.findViewById(R.id.ipd);

    }

    @Override
    public void onClick(View v) {

    }
}
public class cartAdapter extends RecyclerView.Adapter<cartViewholder>{
   private List<Order>cart=new ArrayList<>();
    private Context context;

    public cartAdapter(List<Order> cart, Context context) {
        this.cart = cart;
        this.context = context;
    }

    @NonNull

    @Override
    public cartViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.productcart,viewGroup,false);
        return new cartViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewholder cartViewholder, int position) {
        TextDrawable drawable=TextDrawable.builder()
                .buildRound(""+cart.get(position).getQuantity(), Color.RED);
                  cartViewholder.cimage.setImageDrawable(drawable);

        Locale locale=new Locale("en","us");
        NumberFormat format=NumberFormat.getCurrencyInstance(locale);
        int price =(Integer.parseInt(cart.get(position).getPrice()))*(Integer.parseInt(cart.get(position).getQuantity()));
        cartViewholder.cprice.setText(format.format(price));
        cartViewholder.cTitle.setText(cart.get(position).getProductName());
         Picasso.get().load(cart.get(position).getImage()).into(cartViewholder.cimage2);

    }

    @Override
    public int getItemCount() {
        return cart.size();
    }
}
