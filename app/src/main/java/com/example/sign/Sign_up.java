package com.example.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sign.Common.common;
import com.example.sign.Model.hello;
import com.google.android.gms.common.internal.service.Common;
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

public class Sign_up extends AppCompatActivity {
    MaterialEditText password1,email1,uname,phon,cardno,receit;
    FButton signint;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password1 = findViewById(R.id.passwordre);
        signint = findViewById(R.id.signint);
        email1 = findViewById(R.id.emailsign);
         uname = findViewById(R.id.usernamesign);
         phon = findViewById(R.id.phne);
         cardno = findViewById(R.id.card900);
         receit = findViewById(R.id.rceipt);

         firebaseDatabase = FirebaseDatabase.getInstance();
         databaseReference = firebaseDatabase.getReference("user");

        firebaseAuth = FirebaseAuth.getInstance();

    signint.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressDialog = new ProgressDialog(Sign_up.this);
            progressDialog.setMessage("Please wait....");
            progressDialog.show();

            final String email = email1.getText().toString().trim();
            final String password = password1.getText().toString().trim();
            final String name = uname.getText().toString().trim();
            final String phone = phon.getText().toString().trim();
            final String cardNo = cardno.getText().toString().trim();
            final String receipt = receit.getText().toString().trim();


            if (TextUtils.isEmpty(name)
            ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage("enter username")
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
            if (TextUtils.isEmpty(phone)
            ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage("enter phone number")
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
            if (TextUtils.isEmpty(cardNo)
            ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage("enter your card number")
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
            if (cardNo.length() < 6) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage("enter card number")
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();


            }


            if (TextUtils.isEmpty(receipt)
            ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage("enter receipt number")
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }

            if (TextUtils.isEmpty(password)
            ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage("enter password")
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();


            }

            if (TextUtils.isEmpty(email)
            ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                builder.setMessage(R.string.signup_error_message)
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            if (password.length() < 6) {
                Toast.makeText(Sign_up.this, "password too short", Toast.LENGTH_SHORT).show();

            }


            firebaseAuth.createUserWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                final hello Hello = new hello(name, cardNo,phone,receipt,email);
                                databaseReference.child("UId").setValue(Hello).

                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Sign_up.this, "registration sucessful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Sign_up.this, Home.class);
                                                    common.currentuser = Hello;
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
                                                    builder.setMessage(task.getException().getMessage())
                                                            .setTitle(R.string.login_error_title)
                                                            .setPositiveButton(android.R.string.ok, null);
                                                    AlertDialog dialog = builder.create();
                                                    dialog.show();
                                                    Toast.makeText(Sign_up.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(Sign_up.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
       /*         }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });
        }

    });
}*/

    });}



    public void log(View view)
{
    Intent intent=new Intent(this,Sign_in.class);

    startActivity(intent);
}

}


      /*  signint.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog=new ProgressDialog(Sign_up.this);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
    });}
*/
/*databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

       if (dataSnapshot.child(email).exists())
       {
           progressDialog.dismiss();
           Toast.makeText(Sign_up.this, "email exits", Toast.LENGTH_SHORT).show();
       }

       else
           {*/

/*
firebaseAuth.createUserWithEmailAndPassword(email,password)
       .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {

               if (task.isSuccessful())
               {
                   progressDialog.dismiss();
                   hello user=new hello(phone,username,email);

                   databaseReference.child(firebaseAuth.getCurrentUser().getUid()).
                           setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                           Toast.makeText(Sign_up.this, "Registration sucessful", Toast.LENGTH_SHORT).show();
                       }
                       else {
                               Toast.makeText(Sign_up.this, "registration failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });


               }
                           else {
                               Toast.makeText(Sign_up.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
           }});

    }

}

 */
/*   }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});*//*



        */
/*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child(phone1.getText().toString()).exists())
                {  progressDialog.dismiss();
                   hello user=new hello(username1.getText().toString(),password1.getText().toString());
                    databaseReference.child(phone1.getText().toString()).setValue(user);
                    Toast.makeText(Sign_up.this, "Registration sucessful", Toast.LENGTH_SHORT).show();

                }
                else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Sign_up.this, "Phone Number exits", Toast.LENGTH_LONG).show();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*//*


*/
/*
    }
});
*//*




*/