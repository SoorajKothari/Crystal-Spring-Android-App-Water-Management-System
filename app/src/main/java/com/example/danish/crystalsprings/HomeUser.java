package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.danish.crystalsprings.Prevalant.Prevalant;

import io.paperdb.Paper;

public class HomeUser extends AppCompatActivity  implements View.OnClickListener {

    private CardView uprodcuts,uhistory,uorder,utrack,uprofile,ucontact,uaboutus,udelete,ulogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        uprodcuts = (CardView)findViewById(R.id.cardviewid);
        uhistory = (CardView)findViewById(R.id.cardviewid1);
        utrack = (CardView)findViewById(R.id.cardviewid2);
        uorder = (CardView)findViewById(R.id.cardviewid3);
        uprofile = (CardView)findViewById(R.id.cardviewid4);
        ucontact = (CardView)findViewById(R.id.cardviewid5);
        uaboutus = (CardView)findViewById(R.id.cardviewid6);
        udelete = (CardView)findViewById(R.id.cardviewilatestdelete);

        ulogout = (CardView)findViewById(R.id.cardviewlogoutr);

        uprodcuts.setOnClickListener(this);
        uhistory.setOnClickListener(this);
        utrack.setOnClickListener(this);
        uorder.setOnClickListener(this);
        uprofile.setOnClickListener(this);
        ucontact.setOnClickListener(this);
        uaboutus.setOnClickListener(this);
        udelete.setOnClickListener(this);
        ulogout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.cardviewid:i = new Intent(this, Products.class);startActivity(i);break;
            case R.id.cardviewid1:i = new Intent(this, PriceActivity.class);startActivity(i);break;
            case R.id.cardviewid2:i = new Intent(this, TrackOrder.class);startActivity(i);break;
            case R.id.cardviewid3:i = new Intent(this, OrderNowActvity.class);startActivity(i);break;
            case R.id.cardviewid5:i = new Intent(this, ContactUs.class);startActivity(i);break;
            case R.id.cardviewid4:i = new Intent(this, ProfileUserActivity.class);startActivity(i);break;
            case R.id.cardviewid6:i = new Intent(this,AboutUs.class);startActivity(i);break;
            case R.id.cardviewilatestdelete: i = new Intent(this,deleteOrderCustomer.class);startActivity(i);break;
            case R.id.cardviewlogoutr:
                Paper.book().write(Prevalant.usernamekey,"UserName");
                Paper.book().write(Prevalant.userpasskey,"UserPasswrod");
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
                default: break;
        }
    }
}
