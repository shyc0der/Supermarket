package com.example.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.sign.Common.common;
import com.example.sign.Model.hello;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class Sign_in extends AppCompatActivity {
MaterialEditText password3,email3;
FButton signin,reset4;
FirebaseAuth firebaseAuth;


FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
firebaseDatabase=FirebaseDatabase.getInstance();
databaseReference=firebaseDatabase.getReference("user");

    password3=findViewById(R.id.password);
     email3=findViewById(R.id.email);
signin=findViewById(R.id.logint);
firebaseAuth=FirebaseAuth.getInstance();
        reset4=findViewById(R.id.reset);

        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(Sign_in.this);
                progressDialog.setMessage("Please waiting.....");
                progressDialog.show();

                final String email = email3.getText().toString().trim();
                final String password = password3.getText().toString().trim();

                if (TextUtils.isEmpty(password)
                ) {progressDialog.dismiss();
                    Toast.makeText(Sign_in.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)
                ) {progressDialog.dismiss();
                    Toast.makeText(Sign_in.this, "Enter email_Address!", Toast.LENGTH_SHORT).show();
                    return;
                }
      firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                           new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
       progressDialog.dismiss();
     if (task.isSuccessful()) {

             Toast.makeText(Sign_in.this, "login sucesssful", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(Sign_in.this, Home.class);
                   startActivity(intent);
                   finish();
               }

     else {
           if (password.length() < 6) {
               password3.setError("the password is too short");

           } else {
               Toast.makeText(Sign_in.this, "Authenication failed", Toast.LENGTH_SHORT).show();
           }
       }
   }
}
);

           }
           });}
/*signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog=new ProgressDialog(Sign_in.this);
        progressDialog.setMessage("Please waiting.....");
        progressDialog.show();
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      progressDialog.dismiss();
      if (dataSnapshot.child(phone.getText().toString()).exists()) {

          hello ello = dataSnapshot.child(phone.getText().toString()).getValue(hello.class);

          if (ello.getPassword().equals(password.getText().toString())) {

              Toast.makeText(Sign_in.this, "Login is succesful", Toast.LENGTH_SHORT).show();
phone.setText("");
              password.setText("");
          }
          else {

              Toast.makeText(Sign_in.this, "check your password", Toast.LENGTH_SHORT).show();
          }
      }
    else
      {
          Toast.makeText(Sign_in.this, "username incorrect", Toast.LENGTH_SHORT).show();
      }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }
});*/
    public void reset(View view){
        Intent intent=new Intent(this,ResetPassword.class);
        startActivity(intent);
    }
}
