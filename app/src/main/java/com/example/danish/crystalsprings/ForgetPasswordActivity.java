package com.example.danish.crystalsprings;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.example.danish.crystalsprings.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class ForgetPasswordActivity extends AppCompatActivity {
    private String Usernamekey;
    private Button button;
    private EditText t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


    t1 = (EditText)findViewById(R.id.SecurityNameForgetpass);
    t2 = (EditText)findViewById(R.id.SecurityemailForgetpass);
    button = (Button)findViewById(R.id.Forgetpass_btn);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String secname = t1.getText().toString();
            final String secemail = t2.getText().toString();

            if(TextUtils.isEmpty(secname) || TextUtils.isEmpty(secemail))
            {
                Toast.makeText(ForgetPasswordActivity.this, "Fill in all fields clearly", Toast.LENGTH_SHORT).show();
            }

            final DatabaseReference rootref;
            rootref = FirebaseDatabase.getInstance().getReference();

            rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Usernamekey = Paper.book().read((CurrentUser.usernamekey));
                    Users userdata = dataSnapshot.child("Users").child(Usernamekey).getValue(Users.class);
                    String ysecname = userdata.getUsecname();
                    String ysecemail = userdata.getEmail();
                    String pass = userdata.getPassword();

                    if(ysecname.equals(secname))
                    {
                        if(ysecemail.equals(secemail))
                        {
                            sendpassword(pass);
                        }
                        else
                        {
                            Toast.makeText(ForgetPasswordActivity.this, "Incorrect email", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(ForgetPasswordActivity.this, "Incorrect Security name", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    });
    }

    private void sendpassword(String pass) {
        Toast.makeText(this, "Password: "+ pass, Toast.LENGTH_SHORT).show();
    }
}
