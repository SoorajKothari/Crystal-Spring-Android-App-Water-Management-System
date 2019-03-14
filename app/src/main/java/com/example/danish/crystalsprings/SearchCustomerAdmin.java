package com.example.danish.crystalsprings;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchCustomerAdmin extends AppCompatActivity {

    DatabaseReference Rootref;
    private EditText e1;
    private TextView t1,t2,t3,t4,t5,t6;
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer_admin);

        e1 = (EditText)findViewById(R.id.CustomerUsernameSearch);
        t1 = (TextView)findViewById(R.id.searchname);
        t2 = (TextView)findViewById(R.id.searchemail);
        t3 = (TextView)findViewById(R.id.searchphone);
        t4 = (TextView)findViewById(R.id.searchaddress);
        t5 = (TextView)findViewById(R.id.searchtoorder);
        t6 = (TextView)findViewById(R.id.searchtlastorder);



        search = (Button)findViewById(R.id.SearchUserButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = e1.getText().toString();
                Rootref = FirebaseDatabase.getInstance().getReference();
                Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("Users").child(name).exists())
                        {
                        String uname = dataSnapshot.child("Users").child(name).child("name").getValue().toString();
                        t1.setText(uname);
                        String uemail = dataSnapshot.child("Users").child(name).child("email").getValue().toString();
                        t2.setText(uemail);
                        String uphone = dataSnapshot.child("Users").child(name).child("phone").getValue().toString();
                        t3.setText(uphone);
                        String uaddress = dataSnapshot.child("Users").child(name).child("address").getValue().toString();
                        t4.setText(uaddress);
                        String uorders = dataSnapshot.child("Users").child(name).child("orderno").getValue().toString();
                        int orderno = Integer.parseInt(uorders);
                        int f = orderno - 1000;
                        uorders = Integer.toString(f);
                        t5.setText("Total orders: " + uorders);
                        if(f==0)
                        {
                            t6.setText("No Orders yet");
                        }
                        else
                        {
                            orderno--;
                            String prev = Integer.toString(orderno);
                            String date = dataSnapshot.child("orders").child(name).child(prev).child("date").getValue().toString();
                            t6.setText("Last order: " + date);
                        }


                        }
                        else
                        {
                            Toast.makeText(SearchCustomerAdmin.this, "Username Doesnot exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
