package com.example.sign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;


public class MainActivity extends AppCompatActivity {
FButton btnlogin,btnsign,btnproduct;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
btnlogin=findViewById(R.id.login);
btnsign=findViewById(R.id.sign);
textView=findViewById(R.id.first);
btnproduct=findViewById(R.id.products);
btnsign.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,Sign_up.class);
                startActivity(intent);
    }
});
btnlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(MainActivity.this,Sign_in.class);
                startActivity(intent1);

    }
});

        btnproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this, Monthly.class);
                startActivity(intent1);

            }
        });

    }
}
