package com.example.danish.crystalsprings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.danish.crystalsprings.Prevalant.CurrentUser;
import com.example.danish.crystalsprings.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class EaxmpleDialog extends AppCompatDialogFragment {


    private Examplediloglistener examplediloglistener;
    private String oldadd,newadd;
    private EditText youraddress;
    private Spinner mpsinner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)

        .setTitle("Confirm Your Address")
        .setMessage("Type new address below and press Change")
        .setCancelable(false)
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newadd = youraddress.getText().toString();
                        examplediloglistener.Applyaddress(newadd);
                    }
                });
        mpsinner = view.findViewById(R.id.spinnerfrom);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinnerfrom));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mpsinner.setAdapter(adapter);
        youraddress = view.findViewById(R.id.dileveryAddress);
        oldadd = youraddress.getText().toString();

        Setuseraddress();
    return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
     try {
         examplediloglistener = (Examplediloglistener) context;
     }catch (ClassCastException e)
     {
         throw new ClassCastException(context.toString()+ "Examplediloglistener");
     }

    }

    public interface Examplediloglistener
    {
        void Applyaddress(String address);
    }

    private void Setuseraddress() {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String  Usernamekey = Paper.book().read((CurrentUser.usernamekey));
                Users userdata = dataSnapshot.child("Users").child(Usernamekey).getValue(Users.class);
                String add = userdata.getAddress();
                youraddress.setText(add);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
