package com.example.sign;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.sign.Model.Order;
import com.example.sign.Model.product;
import com.example.sign.Database.database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class detailProduct extends AppCompatActivity {
TextView Dprice,Dquantity,Dtitle;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton floatingActionButton;
    private FirebaseAuth mAuth;
ImageView Dimage;
String productId="";
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
ElegantNumberButton numberButton;
    product currentproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
    firebaseDatabase=FirebaseDatabase.getInstance();
    databaseReference=firebaseDatabase.getReference("Product");


    Dimage=findViewById(R.id.detail_image);
    numberButton=findViewById(R.id.detail_button);
    numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
        Dtitle=findViewById(R.id.detail_price);
         Dprice=findViewById(R.id.detail_price);
        Dquantity=findViewById(R.id.detail_desc);
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandableTitle);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedAppbar);
        floatingActionButton=findViewById(R.id.cart);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new database(getBaseContext()).addcart(new Order(
                     productId,
                    currentproduct.getName(),
                    numberButton.getNumber(),
                    currentproduct.getPrice(),
                    currentproduct.getDiscount(),
                        currentproduct.getImage()

                ));
                Toast.makeText(detailProduct.this, "added to cart", Toast.LENGTH_LONG).show();
            }
        });


        if (getIntent() != null){
            productId=getIntent().getStringExtra("ProductId");

        }
        if (!productId.isEmpty())
        {
            getproductDetail(productId);
        }
    }

    private void getproductDetail(String productId) {

databaseReference.child(productId).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        currentproduct=dataSnapshot.getValue(product.class);
        collapsingToolbarLayout.setTitle(currentproduct.getName());
        Picasso.get().load( currentproduct.getImage()).into(Dimage);
        Dtitle.setText( currentproduct.getName());
        Dprice.setText( currentproduct.getPrice());
        Dquantity.setText( currentproduct.getDescription());


    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }
}
