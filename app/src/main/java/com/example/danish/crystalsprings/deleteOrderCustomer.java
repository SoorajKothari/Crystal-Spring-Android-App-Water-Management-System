package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class deleteOrderCustomer extends AppCompatActivity {
    private static final String TAG = "MyAppTag";
    private String Usernamekey;
    DatabaseReference reference;
    EditText e1;
    Button button;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order_customer);
        Usernamekey = Paper.book().read((CurrentUser.usernamekey));
    reference = FirebaseDatabase.getInstance().getReference();

    e1 = (EditText)findViewById(R.id.ordertodelete);
    t1 = (TextView)findViewById(R.id.Commentdeleteorder);

  button = (Button)findViewById(R.id.orderdeleteeehButton);
  button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          String order = e1.getText().toString();
          if(TextUtils.isEmpty(order))
          {
              Toast.makeText(deleteOrderCustomer.this, "Fill orderno first", Toast.LENGTH_SHORT).show();
          }
          else
          {
             deleteorderfunc(order);
          }
      }
  });
    }

    private void deleteorderfunc(final String order) {

    reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.child("orders").child(Usernamekey).child(order).exists())
            {
            String status = dataSnapshot.child("orders").child(Usernamekey).child(order).child("status").getValue().toString();
                Log.d(TAG, "onDataChange: "+ status);
            if(status.contentEquals("Processing"))
            {
                reference.child("orders").child(Usernamekey).child(order).child("status").setValue("User Cancelled");
                Intent i;
                i = new Intent(deleteOrderCustomer.this,TrackOrder.class);
                Toast.makeText(deleteOrderCustomer.this, "Order Cancelled", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
            else
            {
                Toast.makeText(deleteOrderCustomer.this, "Sorry you cannot cancel now", Toast.LENGTH_SHORT).show();
            }

            }
            else
            {
                Toast.makeText(deleteOrderCustomer.this, "Orderno Doesnot Exist", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }
}
