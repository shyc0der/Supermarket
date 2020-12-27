package com.example.sign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sign.Model.viewOrder;
import com.example.sign.ViewHolder.Vviewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Monthly extends AppCompatActivity {
    RecyclerView recyclerV;
    FirebaseDatabase firebaseDatabase;
    FirebaseRecyclerAdapter<viewOrder, Vviewholder> adapter;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_monthly);
       mAuth = FirebaseAuth.getInstance();
       firebaseDatabase = FirebaseDatabase.getInstance();
       String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

       databaseReference = firebaseDatabase.getReference("Orders").child("documentId").child("orders");

       recyclerV = findViewById(R.id.recycle_view);
       recyclerV.setHasFixedSize(true);
       recyclerV.setLayoutManager(new LinearLayoutManager(this));
       String email1 = mAuth.getCurrentUser().getEmail();

   }
    @Override

     protected void onStart() {
        super.onStart();


           adapter = new FirebaseRecyclerAdapter<viewOrder, Vviewholder>(viewOrder.class,
                   R.layout.viewcart, Vviewholder.class, databaseReference) {
               @Override
               protected void populateViewHolder(Vviewholder viewHolder, viewOrder model, int position) {

                   viewHolder.setDetails
                           (getApplicationContext(),model.getDiscount(),model.getImage(),model.getPrice(),model.getProductName(),model.getQuantity());
               }

           };
           recyclerV.setAdapter(adapter);

       }
    }


