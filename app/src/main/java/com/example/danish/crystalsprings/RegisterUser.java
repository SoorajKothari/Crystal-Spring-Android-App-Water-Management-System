package com.example.danish.crystalsprings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.Prevalant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity {


    private Button createaccountb;
    private EditText username,email,password,address,phone,securityname;
    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        createaccountb = (Button)findViewById(R.id.reg_btn);
        username = (EditText)findViewById(R.id.reg_Username_input);
        email = (EditText)findViewById(R.id.reg_email_input);
        password = (EditText)findViewById(R.id.reg_pass_input);
        address = (EditText)findViewById(R.id.reg_address_input);
        phone = (EditText)findViewById(R.id.reg_phone_input);
        securityname = (EditText)findViewById(R.id.reg_security_input);
        loadingbar = new ProgressDialog(this);

        createaccountb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount(){
        String uemail = email.getText().toString();
        String uname = username.getText().toString();
        String upass = password.getText().toString();
        String uaddress = address.getText().toString();
        String uphone = phone.getText().toString();
        String usecname = securityname.getText().toString();
        if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(uemail) || TextUtils.isEmpty(upass) || TextUtils.isEmpty(uaddress)
                 || TextUtils.isEmpty(uphone) || TextUtils.isEmpty(usecname))
        {
            Toast.makeText(this,"Kindly fill all the fields first",Toast.LENGTH_LONG).show();
        }
        else
        {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait! until we create your account");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            Validateusername(uname,uemail,upass,uaddress,uphone,usecname);

        }
    }





    private void Validateusername(final String name, final String email, final String password, final String address,final String phone,final String usecname)
    {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        final int orderno = 1000;

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if( !(dataSnapshot.child("Users").child(name).exists())){
                    HashMap<String,Object> userdatamap = new HashMap<>();
                    userdatamap.put("name",name);
                    userdatamap.put("password",password);
                    userdatamap.put("email",email);
                    userdatamap.put("address",address);
                    userdatamap.put("phone",phone);
                    userdatamap.put("orderno",orderno);
                    userdatamap.put("usecname",usecname);
                    Rootref.child("Users").child(name).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "Your account has been created", Toast.LENGTH_SHORT).show();

                                        loadingbar.dismiss();
                                        Intent intent = new Intent(RegisterUser.this,Login.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterUser.this, "Error! Kindly check you internet Connection and try again", Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();
                                    }
                                }
                            });
                }else
                {
                    Toast.makeText(RegisterUser.this, "Username " + name + " Already in use", Toast.LENGTH_LONG).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

