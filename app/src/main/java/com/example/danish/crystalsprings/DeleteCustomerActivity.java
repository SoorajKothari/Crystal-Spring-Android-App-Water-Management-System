package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteCustomerActivity extends AppCompatActivity {

    private EditText uname;
    private Button delete,cancel;
    DatabaseReference Rootref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customer);

    uname = (EditText)findViewById(R.id.CustomerUsernameDelete);
    cancel = (Button)findViewById(R.id.cancelbuttoncustomerdelete);
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i;
            Toast.makeText(DeleteCustomerActivity.this, "Cancel Pressed", Toast.LENGTH_SHORT).show();
            i = new Intent(DeleteCustomerActivity.this,AdminViewLogin.class);
            startActivity(i);
        }
    });

    delete = (Button)findViewById(R.id.deleteConfrimbutton);

    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String name = uname.getText().toString();
            Rootref = FirebaseDatabase.getInstance().getReference();
            Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child("Users").child(name).exists())
                    {
                        Rootref.child("Users").child(name).removeValue();


                        if(dataSnapshot.child("orders").child(name).exists())
                        {
                            Rootref.child("orders").child(name).removeValue();
                        }
                        Intent i;
                        Toast.makeText(DeleteCustomerActivity.this, "Removed Successfully", Toast.LENGTH_SHORT).show();
                        i = new Intent(DeleteCustomerActivity.this,AdminViewLogin.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(DeleteCustomerActivity.this, "Username Dosenot Exist Kindly Confirm Details", Toast.LENGTH_SHORT).show();
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
