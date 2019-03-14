package com.example.danish.crystalsprings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.service.autofill.RegexValidator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.Prevalant;
import com.example.danish.crystalsprings.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog loadingbar;
    private Button loginbtn,singupbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingbar = new ProgressDialog(this);
        loginbtn = (Button) findViewById(R.id.main_login_btn);
        singupbtn = (Button) findViewById(R.id.main_singup_btn);
        Paper.init(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });


        singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterUser.class);
                startActivity(intent);
            }
        });

        String Usernamekey = Paper.book().read((Prevalant.usernamekey));
        String Userpasskey = Paper.book().read((Prevalant.userpasskey));

        if(Usernamekey != "" && Userpasskey !="")
        {
            if(!TextUtils.isEmpty(Usernamekey) && !TextUtils.isEmpty(Userpasskey))
            {
                AllowAccess(Usernamekey,Userpasskey);


                loadingbar.setTitle("Already Logged in");
                loadingbar.setMessage("Just a moment...");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();
            }
        }
    }

    private void AllowAccess(final String uname,final String upass) {

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();


        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(uname).exists())
                {
                    Users userdata = dataSnapshot.child("Users").child(uname).getValue(Users.class);
                    if(userdata.getName().equals(uname))
                    {
                        if(userdata.getPassword().equals(upass))
                        {

                            loadingbar.dismiss();
                            Intent intent = new Intent(MainActivity.this,HomeUser.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "incorrect Password", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Cannot Remember", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
