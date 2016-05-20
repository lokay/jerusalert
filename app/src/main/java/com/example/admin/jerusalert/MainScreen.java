package com.example.admin.jerusalert;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainScreen extends AppCompatActivity {

    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        final Context context = this;
        ImageButton btn = (ImageButton) findViewById(R.id.report_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        //other_report_button
        ImageButton btnNear = (ImageButton) findViewById(R.id.other_report_button);
        btnNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });


        //other_report_button
        btn = (ImageButton) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pop-up
                phoneNumber = (EditText)findViewById(R.id.phoneNumber);
                AlertDialog alertDialog = new AlertDialog.Builder(MainScreen.this).create();
                alertDialog.setTitle("הכנס מס פלאפון");
                alertDialog.setMessage("הכנס מס פלאפון");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(context, MyReqStatus.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();



            }
        });

    }
}
