package com.example.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sign.Common.common;
import com.example.sign.Interface.ItemClickListener;
import com.example.sign.Model.Category;
import com.example.sign.ViewHolder.MenuViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    TextView mail,name;
    ImageView imageb;
    RecyclerView recyclermenu;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseRecyclerAdapter<Category, MenuViewholder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Category");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(Home.this,productCart.class);
               startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String email1 = mAuth.getCurrentUser().getEmail();
        String username=mAuth.getCurrentUser().getDisplayName();

      View headerview=navigationView.getHeaderView(0);

      name=headerview.findViewById(R.id.head_name);
      name.setText(username);

       mail=headerview.findViewById(R.id.head_mail);
       mail.setText(email1);

       imageb=findViewById(R.id.head_image);
       //implement image here
        recyclermenu=findViewById(R.id.recyle_menu);
        recyclermenu.setHasFixedSize(true);
        recyclermenu.setLayoutManager(new LinearLayoutManager(this));

        loadMenu();
            }

    private void loadMenu() {

        adapter=new FirebaseRecyclerAdapter<Category, MenuViewholder>(Category.class,R.layout.menu_item,
                        MenuViewholder.class,databaseReference) {
            @Override
            protected void populateViewHolder(MenuViewholder viewHolder, Category model, int position) {
            viewHolder.textView.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.imageView);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent=new Intent(Home.this,itemProduct.class);

                        intent.putExtra("CategoryId",adapter.getRef(position).getKey());

                        startActivity(intent);
                    }
                });
            }
        };

    recyclermenu.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_user) {
            Intent intent=new Intent(Home.this,userinfo.class);
            startActivity(intent);

        } else if (id == R.id.nav_notification) {
            Intent cartIntent=new Intent(Home.this,productCart.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_month) {
            Intent intent=new Intent(Home.this,Monthly.class);
            startActivity(intent);

        } else if (id == R.id.nav_logOut) {

            Intent signIn=new Intent(Home.this,Sign_in.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
