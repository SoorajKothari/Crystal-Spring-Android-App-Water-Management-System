package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.Prevalant;

import java.util.Calendar;

import io.paperdb.Paper;

public class AdminViewLogin extends AppCompatActivity implements View.OnClickListener {


    private CardView c1,c3,c4,c5,c6,c7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_login);
        Toast.makeText(this, "You are admin", Toast.LENGTH_SHORT).show();


        c1 = (CardView)findViewById(R.id.SearchAdmincardview);

        c3 = (CardView)findViewById(R.id.Adminprofilecardview);
        c4 = (CardView)findViewById(R.id.insertcustomerAdmincardview);
        c5 = (CardView)findViewById(R.id.deleteCustomerAdmincardview);
        c6 = (CardView)findViewById(R.id.logoutAdmincardview);
        c7 = (CardView)findViewById(R.id.StatusOrder);

    c1.setOnClickListener(this);

    c3.setOnClickListener(this);
    c4.setOnClickListener(this);
    c5.setOnClickListener(this);
    c6.setOnClickListener(this);
    c7.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()) {
            case R.id.SearchAdmincardview: i = new Intent(this,SearchCustomerAdmin.class);startActivity(i);break;
            case R.id.insertcustomerAdmincardview: i = new Intent(this, RegisterUser.class);startActivity(i);break;
            case R.id.deleteCustomerAdmincardview: i = new Intent(this,DeleteCustomerActivity.class);startActivity(i);break;
            case R.id.Adminprofilecardview: i = new Intent(this,ProfileAdminActivity.class);startActivity(i);break;
            case R.id.logoutAdmincardview:
                Paper.book().write(Prevalant.usernamekey,"UserName");
                Paper.book().write(Prevalant.userpasskey,"UserPasswrod");
                Intent intent = new Intent(AdminViewLogin.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.StatusOrder: i = new Intent(this,OrderStatusForAdmin.class);startActivity(i);break;
            default: break;
        }
    }
}
