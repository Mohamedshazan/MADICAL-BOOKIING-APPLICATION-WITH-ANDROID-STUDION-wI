package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        CardView exit=findViewById(R.id.cardEXSID);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, WalcomeActivity.class));
            }
        });

        CardView findDoctor=findViewById(R.id.cardFINDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });


        CardView labTest=findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });

  CardView orderDetails=findViewById(R.id.cardODERDetails)   ;
  orderDetails.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
      }
  });

        CardView buyMedicine=findViewById(R.id.cardBUYMedicine)   ;
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,buymedicineActivity.class));
            }

        });
        CardView health=findViewById(R.id.cardHealthDoctor)   ;
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthArticleActivity.class));
            }
        });


    }
}