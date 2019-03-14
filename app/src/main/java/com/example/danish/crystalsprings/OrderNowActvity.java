package com.example.danish.crystalsprings;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.example.danish.crystalsprings.Prevalant.Prevalant;
import com.example.danish.crystalsprings.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import io.paperdb.Paper;

public class OrderNowActvity extends AppCompatActivity implements EaxmpleDialog.Examplediloglistener {


    private static final String TAG = "MyAppTag";
    private String Usernamekey;
    int a, b, c, d, e, f, g;
    private String orderday;
    private int orderno;
    private String savecurrendate, strcounter, strquantiy, strprice,strstatus="Processing";
    private Button button, reset, payaddress;
    private EditText onegallon, twogallon, threegatllon, fivegallon, tentank, twnetytank, fiftytank;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    private int count = 0, quantity = 0;
    private TextView t21, t22, t23, t24, t25, t26;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now_actvity);


        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t6 = (TextView) findViewById(R.id.t6);
        t7 = (TextView) findViewById(R.id.t7);
        t8 = (TextView) findViewById(R.id.t8);
        t9 = (TextView) findViewById(R.id.t9);
        t10 = (TextView) findViewById(R.id.t10);


        t21 = (TextView) findViewById(R.id.textbill1);
        t22 = (TextView) findViewById(R.id.ordernotext);
        t23 = (TextView) findViewById(R.id.Datebill);
        t24 = (TextView) findViewById(R.id.totalquantity);
        t25 = (TextView) findViewById(R.id.totalthings);
        t26 = (TextView) findViewById(R.id.totalprice);

        onegallon = (EditText) findViewById(R.id.onegallon);
        twogallon = (EditText) findViewById(R.id.twogallon);
        threegatllon = (EditText) findViewById(R.id.threegallon);
        fivegallon = (EditText) findViewById(R.id.fiveugallon);

        tentank = (EditText) findViewById(R.id.tenliters);
        twnetytank = (EditText) findViewById(R.id.twentyliters);
        fiftytank = (EditText) findViewById(R.id.fiftyliters);


        reset = (Button) findViewById(R.id.resetbutton);
        payaddress = (Button) findViewById(R.id.payondileveybutton);

        button = (Button) findViewById(R.id.Confirm_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String onegaln = onegallon.getText().toString();
                String twogaln = twogallon.getText().toString();
                String threegaln = threegatllon.getText().toString();
                String fivegaln = fivegallon.getText().toString();
                String tentan = tentank.getText().toString();
                String twintytan = twnetytank.getText().toString();
                String fiftytan = fiftytank.getText().toString();


                if (TextUtils.isEmpty(onegaln) && TextUtils.isEmpty(twogaln) && TextUtils.isEmpty(threegaln) && TextUtils.isEmpty(fivegaln)

                        && TextUtils.isEmpty(tentan) && TextUtils.isEmpty(twintytan) && TextUtils.isEmpty(fiftytan)) {
                    Toast.makeText(OrderNowActvity.this, "You haven't placed any order yet", Toast.LENGTH_LONG).show();
                } else {
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    t6.setVisibility(View.INVISIBLE);
                    t7.setVisibility(View.INVISIBLE);
                    t8.setVisibility(View.INVISIBLE);
                    t9.setVisibility(View.INVISIBLE);
                    t10.setVisibility(View.INVISIBLE);


                    onegallon.setVisibility(View.INVISIBLE);
                    twogallon.setVisibility(View.INVISIBLE);
                    threegatllon.setVisibility(View.INVISIBLE);
                    fivegallon.setVisibility(View.INVISIBLE);
                    tentank.setVisibility(View.INVISIBLE);
                    twnetytank.setVisibility(View.INVISIBLE);
                    fiftytank.setVisibility(View.INVISIBLE);

                    button.setVisibility(View.INVISIBLE);

                    t21.setVisibility(View.VISIBLE);
                    t22.setVisibility(View.VISIBLE);
                    t23.setVisibility(View.VISIBLE);
                    t24.setVisibility(View.VISIBLE);
                    t25.setVisibility(View.VISIBLE);
                    t26.setVisibility(View.VISIBLE);
                    reset.setVisibility(View.VISIBLE);
                    payaddress.setVisibility(View.VISIBLE);


                    payaddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OpenDialog();
                        }
                    });


                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat currentdate = new SimpleDateFormat("dd,MM,yyyy");
                    savecurrendate = currentdate.format(calendar.getTime());

                    orderday = savecurrendate;
                    t23.setText("Date: " + savecurrendate);
                    int price = 0;
                    if (!TextUtils.isEmpty(onegaln)) {
                        a = Integer.parseInt(onegaln);
                        price = price + (a * 40);
                    }

                    if (!TextUtils.isEmpty(twogaln)) {

                        b = Integer.parseInt(twogaln);
                        price = price + (b * 80);
                    }

                    if (!TextUtils.isEmpty(threegaln)) {
                        c = Integer.parseInt(threegaln);
                        price = price + (c * 120);
                    }

                    if (!TextUtils.isEmpty(fivegaln)) {
                        d = Integer.parseInt(fivegaln);
                        price = price + (d * 200);
                    }

                    if (!TextUtils.isEmpty(tentan)) {
                        e = Integer.parseInt(tentan);
                        price = price + (e * 1400);
                    }

                    if (!TextUtils.isEmpty(twintytan)) {
                        f = Integer.parseInt(twintytan);
                        price = price + (f * 2800);
                    }

                    if (!TextUtils.isEmpty(fiftytan)) {
                        g = Integer.parseInt(fiftytan);
                        price = price + (g * 7000);
                    }


                    if (!TextUtils.isEmpty(onegaln)) {
                        count++;
                        quantity = quantity + a;
                    }

                    if (!TextUtils.isEmpty(twogaln)) {

                        count++;
                        quantity = quantity + b;
                    }

                    if (!TextUtils.isEmpty(threegaln)) {
                        count++;
                        quantity = quantity + c;
                    }

                    if (!TextUtils.isEmpty(fivegaln)) {
                        count++;
                        quantity = quantity + d;
                    }

                    if (!TextUtils.isEmpty(tentan)) {
                        count++;
                        quantity = quantity + e;
                    }

                    if (!TextUtils.isEmpty(twintytan)) {
                        count++;
                        quantity = quantity + f;
                    }

                    if (!TextUtils.isEmpty(fiftytan)) {
                        count++;
                        quantity = quantity + g;
                    }


                    strcounter = Integer.toString(count);
                    t25.setText("Total items: " + strcounter);

                    strquantiy = Integer.toString(quantity);
                    t24.setText("Total Quantiy: " + strquantiy);

                    strprice = Integer.toString(price);
                    t26.setText("Total Price: " + strprice);

                    ordernowithdatabase();


                }

            }
        });
    }

    private void OpenDialog() {
        EaxmpleDialog eaxmpleDialog = new EaxmpleDialog();
        eaxmpleDialog.show(getSupportFragmentManager(), "Address");

    }

    private void ordernowithdatabase() {


        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usernamekey = Paper.book().read((CurrentUser.usernamekey));
                Users userdata = dataSnapshot.child("Users").child(Usernamekey).getValue(Users.class);
                orderno = userdata.getOrderno();
                String Strorderno = Integer.toString(orderno);
                t22.setText("Orderno: " + Strorderno);
                orderno++;
                final int ordernoo = orderno;
                rootref.child("Users").child(Usernamekey).child("orderno").setValue(ordernoo);
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i;
                        orderno--;
                        strstatus="You Cancelled";
                        final int ordernoo = orderno;
                        rootref.child("Users").child(Usernamekey).child("orderno").setValue(ordernoo);

                        i = new Intent(OrderNowActvity.this, HomeUser.class);
                        startActivity(i);
                        Toast.makeText(OrderNowActvity.this, "Order Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void Applyaddress(final String newadd) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (newadd == null) {
                    Toast.makeText(OrderNowActvity.this, "Kindly fill first", Toast.LENGTH_SHORT).show();
                } else {
                    String Usernamekey = Paper.book().read((CurrentUser.usernamekey));

                    final String orderaddress = newadd;
                    rootref.child("Users").child(Usernamekey).child("address").setValue(orderaddress);
                    Toast.makeText(OrderNowActvity.this, "You will get order delieverd in 12hrs", Toast.LENGTH_SHORT).show();
                    Intent i;

                    i = new Intent(OrderNowActvity.this, HomeUser.class);
                    workupOrder(Usernamekey);

                    startActivity(i);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }




    private void workupOrder(final String name) {
        final DatabaseReference Rootref;
        int i = orderno;
        i--;
        final String ordernum = Integer.toString(i);
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("orders").child(name).child(ordernum).exists())
                {
                    HashMap<String,Object> userdatamap = new HashMap<>();
                    userdatamap.put("status",strstatus);
                    userdatamap.put("orderno",ordernum);
                    userdatamap.put("date",orderday);
                    userdatamap.put("price",strprice);
                    userdatamap.put("items",strcounter);
                    userdatamap.put("quantity",strquantiy);
                    Rootref.child("orders").child(name).child(ordernum).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful())
                       {

                           Toast.makeText(OrderNowActvity.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
                       }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
