package com.example.danish.crystalsprings;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUs extends AppCompatActivity {

    public EditText name,message;
    String myemail = "yourhostemail@gmail.com";
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        name = (EditText)findViewById(R.id.editfeedbackname);
        message = (EditText)findViewById(R.id.edityourMessageinFeedback);

        submit = (Button)findViewById(R.id.submitFeedbackButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = name.getText().toString();
                String umessage = message.getText().toString();

                if (TextUtils.isEmpty(uname)){

                    name.setError("Kindly, Enter Your name");
                    name.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(umessage))
                {
                    message.setError("Kindly, Enter your Feedback/Suggestions");
                    message.requestFocus();
                    return;
                }


                Intent testIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + uname + "&body=" + umessage + "&to=" + myemail);
                testIntent.setData(data);
                startActivity(testIntent);

            }
        });
    }
    }