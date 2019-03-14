package com.example.danish.crystalsprings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Products extends AppCompatActivity {

   private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


    button = (Button)findViewById(R.id.Ordernowinproducts);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i;
            i = new Intent(Products.this,OrderNowActvity.class);
            startActivity(i);
        }
    });
    }
}
