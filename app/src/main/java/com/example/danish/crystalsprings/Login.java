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
import android.widget.TextView;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.example.danish.crystalsprings.Prevalant.Prevalant;
import com.example.danish.crystalsprings.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {

    public String Logineduser;
    private TextView userlink,adminlink,forgetpass;
    private EditText username,userpass;
    private Button loginbtn;
    private com.rey.material.widget.CheckBox checkBoxRememberme;
    private ProgressDialog loadingbar;
    private String parentname = "Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adminlink = (TextView)findViewById(R.id.admin_pannel_link);
        userlink = (TextView)findViewById(R.id.user_pannel_link);
        username = (EditText)findViewById(R.id.login_username_input);
        userpass = (EditText)findViewById(R.id.login_pass_input);
        forgetpass = (TextView)findViewById(R.id.forget_password_link);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(Login.this,ForgetPasswordActivity.class);
                startActivity(i);
            }
        });
        loginbtn = (Button)findViewById(R.id.login_btn);
        loadingbar = new ProgressDialog(this);

        checkBoxRememberme = (com.rey.material.widget.CheckBox) findViewById(R.id.Rememberme_checkbox);
        Paper.init(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginuser();
            }
        });

        adminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginbtn.setText("Login Admin");
                adminlink.setVisibility(View.INVISIBLE);
                userlink.setVisibility(View.VISIBLE);
                forgetpass.setVisibility(View.INVISIBLE);
                checkBoxRememberme.setVisibility(View.INVISIBLE);
                parentname = "Admins";

            }
        });
        userlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginbtn.setText("Login");
                checkBoxRememberme.setVisibility(View.VISIBLE);
                adminlink.setVisibility(View.VISIBLE);
                forgetpass.setVisibility(View.VISIBLE);
                userlink.setVisibility(View.INVISIBLE);
                parentname = "Users";

            }
        });
    }


    public Login() {

    }

    private void Loginuser() {
        String uname = username.getText().toString();
        String upass = userpass.getText().toString();
        if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(upass))
        {
            Toast.makeText(this,"Kindly fill all the fields first",Toast.LENGTH_LONG).show();
        }
        else
        {
            loadingbar.setTitle("Login");
            loadingbar.setMessage("Just a moment");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            Allowaccesstoaccount(uname,upass);
        }
    }

    private void Allowaccesstoaccount(final String uname,final String upass) {
        if(checkBoxRememberme.isChecked())
        {
            Paper.book().write(CurrentUser.usernamekey,uname);
            Paper.book().write(Prevalant.usernamekey,uname);
            Paper.book().write(Prevalant.userpasskey,upass);
        }
        else
        {
            Paper.book().write(CurrentUser.usernamekey,uname);
        }



        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();


        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentname).child(uname).exists())
                {
                    Users userdata = dataSnapshot.child(parentname).child(uname).getValue(Users.class);
                    if(userdata.getName().equals(uname))
                    {
                        if(userdata.getPassword().equals(upass))
                        {
                            if(parentname.equals("Admins"))
                            {
                                Paper.book().write(CurrentUser.usernamekey,uname);
                                Logineduser = userdata.getName();
                                Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                                Intent intent = new Intent(Login.this,AdminViewLogin.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }else
                            {
                                
                                Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                                Intent intent = new Intent(Login.this,HomeUser.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            Toast.makeText(Login.this, "incorrect Password", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "Error! Kindly check your details and try again", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
