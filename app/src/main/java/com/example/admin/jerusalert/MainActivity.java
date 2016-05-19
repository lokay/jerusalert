package com.example.admin.jerusalert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sp = getSharedPreferences("categories", Context.MODE_PRIVATE);


        // animals onClick
        final Context animals = this;
        ImageButton btn = (ImageButton)findViewById(R.id.animals_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "animals");
                e.commit();
                Intent intent = new Intent(animals, Form.class);
                startActivity(intent);
            }
        });

        // electricity onClick
        final Context electricity = this;
        btn = (ImageButton)findViewById(R.id.electricity_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "electricity");
                e.commit();
                Intent intent = new Intent(electricity, Form.class);
                startActivity(intent);
            }
        });

        // water onClick
        final Context water = this;
        btn = (ImageButton)findViewById(R.id.water_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "water");
                e.commit();
                Intent intent = new Intent(water, Form.class);
                startActivity(intent);
            }
        });

        // fire onClick
        final Context fire = this;
        btn = (ImageButton)findViewById(R.id.fire_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "fire");
                e.commit();
                Intent intent = new Intent(fire, Form.class);
                startActivity(intent);
            }
        });

        // sanitation onClick
        final Context sanitation = this;
        btn = (ImageButton)findViewById(R.id.sanitation_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "sanitation");
                e.commit();
                Intent intent = new Intent(sanitation, Form.class);
                startActivity(intent);
            }
        });

        // nature onClick
        final Context nature = this;
        btn = (ImageButton)findViewById(R.id.nature_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "nature");
                e.commit();
                Intent intent = new Intent(nature, Form.class);
                startActivity(intent);
            }
        });

        // traffic onClick
        final Context traffic = this;
        btn = (ImageButton)findViewById(R.id.traffic_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "traffic");
                e.commit();
                Intent intent = new Intent(traffic, Form.class);
                startActivity(intent);
            }
        });

        // security onClick
        final Context security = this;
        btn = (ImageButton)findViewById(R.id.security_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "security");
                e.commit();
                Intent intent = new Intent(security, Form.class);
                startActivity(intent);
            }
        });

        // inspection onClick
        final Context inspection = this;
        btn = (ImageButton)findViewById(R.id.inspection_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = sp.edit();
                e.putString("category", "inspection");
                e.commit();
                Intent intent = new Intent(inspection, Form.class);
                startActivity(intent);
            }
        });

        //String s = sp.getString("1", null);
    }
}
