package com.example.sign;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

public class userinfo extends AppCompatActivity {
MaterialEditText newname,newpass,newemail;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        newemail=findViewById(R.id.newmail);
        newname=findViewById(R.id.newname);
        newpass=findViewById(R.id.newpass);
        mAuth=FirebaseAuth.getInstance();
    }


public  void resetpass(View view)
{
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String newPassword=newpass.getText().toString();
    if (TextUtils.isEmpty(newPassword))
            return;
    user.updatePassword(newPassword).addOnCompleteListener
            (new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful())
               {
                   Toast.makeText(userinfo.this, "password updated", Toast.LENGTH_SHORT).show();
               }
                }
            });
}
    public  void deleteUser(View view)
    {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful())
                Toast.makeText(userinfo.this, "User deleted", Toast.LENGTH_SHORT).show();
        }
    });

    }
    public  void updateName(View view)
    {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String newName=newname.getText().toString();
    if (TextUtils.isEmpty(newName))
        return;
        UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build();
        user.updateProfile(profileChangeRequest).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
          if (task.isSuccessful())
          {
              Toast.makeText(userinfo.this, "User updated", Toast.LENGTH_SHORT).show();
          }
                    }
                }
        );


    }

    public  void updateEmail(View view)
    {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String newMail=newemail.getText().toString();
    if (TextUtils.isEmpty(newMail))
        return;
    user.updateEmail(newMail).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
       if (task.isSuccessful())
       {
           Toast.makeText(userinfo.this, "Email updated", Toast.LENGTH_SHORT).show();
       }
        }
    });

    }

    public  void getuserInfo(View view)
    {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            for (UserInfo profile :user.getProviderData())
            {
                String pddata=profile.getProviderId();
                String uid=profile.getUid();

            String name=profile.getDisplayName();
            String email=profile.getEmail();

                Toast.makeText(this, "id: " +pddata+ "uid: " +uid+ "name: " +name+ "email: " +email, Toast.LENGTH_SHORT).show();
            }
        }


    }

    public  void signout(View view)
    {
mAuth.signOut();

    }
}
