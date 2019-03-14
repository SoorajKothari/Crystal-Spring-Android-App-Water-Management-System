package com.example.danish.crystalsprings.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.danish.crystalsprings.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mview;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mview = itemView;
    }



    public void setOrder(String orderno)
    {
        TextView Ordertitle=(TextView)mview.findViewById(R.id.ordernooncardview);
        Ordertitle.setText("Orderno : "+ orderno);
    }

    public void setitems(String items)
    {
        TextView Ordertitle=(TextView)mview.findViewById(R.id.itemsoncardview);
        Ordertitle.setText("Total items : "+ items);
    }

    public void setquantity(String quantity)
    {
        TextView Ordertitle=(TextView)mview.findViewById(R.id.quatityoncardview);
        Ordertitle.setText("Total Quantity : "+ quantity);
    }


    public void setprice(String price)
    {
        TextView Ordertitle=(TextView)mview.findViewById(R.id.priceoncardview);
        Ordertitle.setText("Total Price : "+ price);
    }

    public void setstatus(String Status)
    {
        TextView Ordertitle=(TextView)mview.findViewById(R.id.statusoncardview);
        Ordertitle.setText("Order Status : "+ Status);
    }

    public void setdate(String date)
    {
        TextView Ordertitle=(TextView)mview.findViewById(R.id.dateoncardview);
        Ordertitle.setText("Order date : "+ date);
    }

}
