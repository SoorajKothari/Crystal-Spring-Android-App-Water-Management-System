package com.example.danish.crystalsprings;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OrderStatusForAdmin extends AppCompatActivity {

    DatabaseReference rootref;
    private EditText e1,e2,e3;
    private TextView t1;
    private Button search,change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status_for_admin);
        rootref = FirebaseDatabase.getInstance().getReference();


        e1 = (EditText)findViewById(R.id.StatusCustomerUsername);
        e2 = (EditText)findViewById(R.id.StatusCustomeroRDERno);
        e3 = (EditText)findViewById(R.id.newstatus);

        t1 = (TextView)findViewById(R.id.currentstatus);


        search = (Button)findViewById(R.id.searchbuttoncustomerstatus);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname = e1.getText().toString();
                final String uorder = e2.getText().toString();
                if (uname != null && uorder != null) {
                    rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("orders").child(uname).exists()) {
                                if (dataSnapshot.child("orders").child(uname).child(uorder).exists()) {
                                    String status = dataSnapshot.child("orders").child(uname).child(uorder).child("status").getValue().toString();
                                    t1.setText("Current Status : " + status);
                                } else {
                                    t1.setText("Invalid Orderno");
                                }
                            } else {
                                Toast.makeText(OrderStatusForAdmin.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
else
                {
                    Toast.makeText(OrderStatusForAdmin.this, "Enter values first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        change = (Button)findViewById(R.id.changstatusbutton);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname = e1.getText().toString();
                final String uorder = e2.getText().toString();
                final String newstatus = e3.getText().toString();
                if(TextUtils.isEmpty(newstatus))
                {
                    Toast.makeText(OrderStatusForAdmin.this, "Enter new status", Toast.LENGTH_SHORT).show();
                }
                else
                {


                   rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         rootref.child("orders").child(uname).child(uorder).child("status").setValue(newstatus);

                           String status = dataSnapshot.child("orders").child(uname).child(uorder).child("status").getValue().toString();
                           t1.setText("Current Status : "+ status);

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                }
            }
        });
    }
}
