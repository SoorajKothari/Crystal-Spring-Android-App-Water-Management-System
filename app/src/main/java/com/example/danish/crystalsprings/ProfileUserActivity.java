package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
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

public class ProfileUserActivity extends AppCompatActivity {

    private String upassword;
    private String uddress;
    private String uphone;
    private String usename;
    private String Usernamekey;
    TextView yourname,youremail;
    EditText youraddress,yourpassword,yourphone,yoursecname;
    Button updateinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);


        yourname = (TextView)findViewById(R.id.yourname);
        youremail = (TextView)findViewById(R.id.youremail);
        youraddress = (EditText) findViewById(R.id.youraddres);
        yourpassword = (EditText)findViewById(R.id.yourpassword);
        yourphone = (EditText)findViewById(R.id.yourphone);
        yoursecname = (EditText)findViewById(R.id.yoursecnum);

        updateinfo = (Button)findViewById(R.id.updateinfoButton);

        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usernamekey = Paper.book().read((CurrentUser.usernamekey));
                Users userdata = dataSnapshot.child("Users").child(Usernamekey).getValue(Users.class);
                String uname = userdata.getName();
                String uemail = userdata.getEmail();
                 upassword = userdata.getPassword();
                 uddress = userdata.getAddress();
                 uphone = userdata.getPhone();
                 usename = userdata.getUsecname();
                yourname.setText(uname);
                youremail.setText(uemail);
                youraddress.setText(uddress);
                yourpassword.setText(upassword);
                yourphone.setText(uphone);
                yoursecname.setText(usename);


                updateinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                String pho = yourphone.getText().toString();
                String pass = yourpassword.getText().toString();
                String address = youraddress.getText().toString();
                String usname = yoursecname.getText().toString();
                if(pho==null || pass==null || address==null || usname==null)
                {
                    Toast.makeText(ProfileUserActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final String p = pho;
                    final String pa = pass;
                    final String a = address;
                    final String u = usname;
                    rootref.child("Users").child(Usernamekey).child("address").setValue(a);
                    rootref.child("Users").child(Usernamekey).child("phone").setValue(p);
                    rootref.child("Users").child(Usernamekey).child("password").setValue(pa);
                    rootref.child("Users").child(Usernamekey).child("usecname").setValue(u);

                    Toast.makeText(ProfileUserActivity.this, "Information Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(ProfileUserActivity.this,HomeUser.class);
                    startActivity(i);

                }

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



     }
}
