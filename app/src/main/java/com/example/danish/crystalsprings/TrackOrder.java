package com.example.danish.crystalsprings;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.example.danish.crystalsprings.model.Users;
import com.example.danish.crystalsprings.model.ViewHolder;
import com.example.danish.crystalsprings.model.blogOrder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class TrackOrder extends AppCompatActivity {
    private String Usernamekey;
    DatabaseReference Rootref,rootref2;
    private TextView t1;
    private RecyclerView mbloglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        t1 = (TextView)findViewById(R.id.TotalOrdersTrack);
        mbloglist = (RecyclerView)findViewById(R.id.RecyclerView);
        mbloglist.setHasFixedSize(true);
        mbloglist.setLayoutManager(new LinearLayoutManager(this));


        Usernamekey = Paper.book().read((CurrentUser.usernamekey));
        Rootref = FirebaseDatabase.getInstance().getReference();
        rootref2 = FirebaseDatabase.getInstance().getReference().child("orders").child(Usernamekey);
        rootref2.keepSynced(true);

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usernamekey = Paper.book().read((CurrentUser.usernamekey));
                Users userdata = dataSnapshot.child("Users").child(Usernamekey).getValue(Users.class);
                int orders = userdata.getOrderno();
                int x = orders - 1000;
                t1.setText("Total Orders : "+ x);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<blogOrder,ViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<blogOrder, ViewHolder>
                (blogOrder.class,R.layout.blog_row,ViewHolder.class,rootref2) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, blogOrder model, int position) {

                viewHolder.setOrder(model.getOrderno());
                viewHolder.setitems(model.getItems());
                viewHolder.setquantity(model.getQuantity());
                viewHolder.setprice(model.getPrice());
                viewHolder.setstatus(model.getStatus());
                viewHolder.setdate(model.getDate());
            }
        };
        mbloglist.setAdapter(firebaseRecyclerAdapter);
    }

 }
