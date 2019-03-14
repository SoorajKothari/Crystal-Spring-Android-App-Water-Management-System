package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.example.danish.crystalsprings.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class ProfileAdminActivity extends AppCompatActivity {

    private String Usernamekey;
    private EditText e1,e2;
    private TextView t1,t2,t3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);


        t1 = (TextView)findViewById(R.id.adminname);
        t2 = (TextView)findViewById(R.id.adminemail);
        t3 = (TextView)findViewById(R.id.adminnpost);

        e1 = (EditText)findViewById(R.id.adminpassworddd);
        e2 = (EditText)findViewById(R.id.adminphone);


        button = (Button)findViewById(R.id.updateinfoButtonforAdmin);

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usernamekey = Paper.book().read((CurrentUser.usernamekey));
                Users userdata = dataSnapshot.child("Admins").child(Usernamekey).getValue(Users.class);
                String uname = dataSnapshot.child("Admins").child(Usernamekey).child("name").getValue().toString();
                String uemail = dataSnapshot.child("Admins").child(Usernamekey).child("email").getValue().toString();
                String upass = dataSnapshot.child("Admins").child(Usernamekey).child("password").getValue().toString();
                String uphone = dataSnapshot.child("Admins").child(Usernamekey).child("phone").getValue().toString();
                String upost = dataSnapshot.child("Admins").child(Usernamekey).child("post").getValue().toString();

                t1.setText(uname);
                t2.setText(uemail);
                t3.setText(upost);

                e1.setText(upass);
                e2.setText(uphone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = e1.getText().toString();
                String phone = e2.getText().toString();

                if(phone==null || pass==null)
                {
                    Toast.makeText(ProfileAdminActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    rootref.child("Admins").child(Usernamekey).child("password").setValue(pass);
                    rootref.child("Admins").child(Usernamekey).child("phone").setValue(phone);

                    Toast.makeText(ProfileAdminActivity.this, "Information Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(ProfileAdminActivity.this,AdminViewLogin.class);
                    startActivity(i);
                }
            }
        });
    }
}
