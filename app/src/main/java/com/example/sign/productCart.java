package com.example.sign;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sign.Common.common;
import com.example.sign.Database.database;
import com.example.sign.Model.Order;
import com.example.sign.Model.Request;
import com.example.sign.ViewHolder.cartAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class productCart extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
RecyclerView recyclerView;
TextView total2;
FButton order4 ,returnprev,ccat;
List<Order> carts=new ArrayList<>();

cartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Orders");
        mAuth = FirebaseAuth.getInstance();

        recyclerView=findViewById(R.id.cart_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        total2=findViewById(R.id.total);
        order4=findViewById(R.id.placeOrder);
        returnprev=findViewById(R.id.others);
        returnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(productCart.this,Home.class);
                startActivity(intent);
            }
        });
        ccat=findViewById(R.id.clearCart);
        ccat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new database(getBaseContext()).clearcart();
                Toast.makeText(productCart.this, "cart cleared", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        order4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showAlertDialog();

            }
        });

loadproductList();
    }

    private void showAlertDialog() {
        final String email1 = mAuth.getCurrentUser().getEmail();
        AlertDialog.Builder builder = new AlertDialog.Builder(productCart.this);
        builder.setMessage("One more step ")
                .setTitle(R.string.adress);
        final  EditText editadress=new EditText(productCart.this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
        );
         editadress.setLayoutParams(layoutParams);
        builder.setView(editadress);
        builder.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request=new Request(
                        email1,
                        editadress.getText().toString(),
                        total2.getText().toString(),
                        carts
                );
                /*String.valueOf(System.currentTimeMillis()*/

                databaseReference.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                new database(getBaseContext()).clearcart();
                Toast.makeText(productCart.this, "Thank you", Toast.LENGTH_SHORT).show();
                    finish();

            }
        });

                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }

    private void loadproductList() {

        carts = new database(this).getcarts();
        adapter = new cartAdapter(carts, this);
        recyclerView.setAdapter(adapter);
        int total = 0;
        for (Order order : carts)
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale("en", "us");
        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(locale);
        total2.setText(numberFormat.format(total));

    }
}
