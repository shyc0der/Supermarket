package com.example.sign;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class ResetPassword extends AppCompatActivity {
    MaterialEditText mail2;
    FButton rest;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        firebaseAuth=FirebaseAuth.getInstance();
         mail2=findViewById(R.id.email2);
        rest=findViewById(R.id.reset);

        rest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(ResetPassword.this);
                progressDialog.setMessage("Please waiting.....");
                progressDialog.show();

                 String email = mail2.getText().toString().trim();

                if (TextUtils.isEmpty(email)
                ) {progressDialog.dismiss();
                    Toast.makeText(ResetPassword.this, "Enter email_Address!", Toast.LENGTH_SHORT).show();
                    return;

                }
                progressDialog.dismiss();
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                                                        @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                                            progressDialog.dismiss();
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(ResetPassword.this, "we have sent instructions to reset email", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(ResetPassword.this, "failed to send ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
        });
    }
}
