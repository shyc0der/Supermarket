package com.example.sign;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.sign.Interface.ItemClickListener;
import com.example.sign.Model.product;
import com.example.sign.ViewHolder.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class itemProduct extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recycler_item;
    String categoryId="";
    FirebaseRecyclerAdapter<product, ViewHolder> firebaseAdapter;
    FirebaseRecyclerAdapter<product, ViewHolder> searchAdapter;
   List<String> searchList=new ArrayList<>();
     MaterialSearchBar materialSearchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_product);

    firebaseDatabase=FirebaseDatabase.getInstance();
    databaseReference=firebaseDatabase.getReference("Product");
    recycler_item=findViewById(R.id.recyle_item);

        recycler_item=findViewById(R.id.recyle_item);
        recycler_item.setHasFixedSize(true);
        recycler_item.setLayoutManager(new LinearLayoutManager(this));

if (getIntent() != null)

    categoryId=getIntent().getStringExtra("CategoryId");

if (!categoryId.isEmpty() && categoryId != null){
    loadItem(categoryId);
}

   materialSearchBar=findViewById(R.id.search_bar);
materialSearchBar.setHint("Search");
 loadSearch();
 materialSearchBar.setLastSuggestions(searchList);
 materialSearchBar.setCardViewElevation(10);
 materialSearchBar.addTextChangeListener(new TextWatcher() {
     @Override
     public void beforeTextChanged(CharSequence s, int start, int count, int after) {

     }

     @Override
     public void onTextChanged(CharSequence s, int start, int before, int count) {
List<String> suggest=new ArrayList<String>();
for (String search:searchList)
{
    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()));
    suggest.add(search);
}
materialSearchBar.setLastSuggestions(suggest);
     }

     @Override
     public void afterTextChanged(Editable s) {

     }
 });
materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
    @Override
    public void onSearchStateChanged(boolean enabled) {
        if (!enabled)
        {
            recycler_item.setAdapter(firebaseAdapter);
        }
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
startSearch(text);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
});

    }

    private void startSearch(CharSequence text) {
     searchAdapter=new FirebaseRecyclerAdapter<product, ViewHolder>
             (product.class,
                R.layout.list,
                     ViewHolder.class,databaseReference.orderByChild("name").equalTo(text.toString())

             ) {
         @Override
         protected void populateViewHolder(ViewHolder viewHolder, product model, int position)   {
             viewHolder.Uprice.setText(model.getPrice());
             viewHolder.Udesc.setText(model.getDescription());
             viewHolder.Utitle.setText(model.getName());
             Picasso.get().load(model.getImage()).into(viewHolder.image2);
             final product fran=model;
             viewHolder.setItemClickListener(new ItemClickListener() {
                 @Override
                 public void onClick(View view, int position, boolean isLongClick) {

                     Intent intent=new Intent(itemProduct.this,detailProduct.class);

                     intent.putExtra("ProductId",searchAdapter.getRef(position).getKey());

                     startActivity(intent);
         }
         });
     }
     };
    recycler_item.setAdapter(searchAdapter);
    }

    private void loadSearch() {
      databaseReference.orderByChild("MenuId").equalTo(categoryId)
              .addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      for (DataSnapshot snapshot:dataSnapshot.getChildren())
                      {
                          product item=snapshot.getValue(product.class);
                          searchList.add(item.getName());
                      }
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
    }

    private void loadItem(String categoryId) {

 firebaseAdapter=new FirebaseRecyclerAdapter<product, ViewHolder>
         (product.class,
                 R.layout.list,
                 ViewHolder.class,
                 databaseReference.orderByChild("MenuId").equalTo(categoryId)) {
     @Override
     protected void populateViewHolder(ViewHolder viewHolder, product model, int position) {
     viewHolder.Uprice.setText(model.getPrice());
     viewHolder.Udesc.setText(model.getDescription());
     viewHolder.Utitle.setText(model.getName());
         Picasso.get().load(model.getImage()).into(viewHolder.image2);
         final product fran=model;
         viewHolder.setItemClickListener(new ItemClickListener() {
             @Override
             public void onClick(View view, int position, boolean isLongClick) {

                 Intent intent=new Intent(itemProduct.this,detailProduct.class);

                 intent.putExtra("ProductId",firebaseAdapter.getRef(position).getKey());

                   startActivity(intent);
             }
         });

    }

 };
recycler_item.setAdapter(firebaseAdapter);

    }


}
