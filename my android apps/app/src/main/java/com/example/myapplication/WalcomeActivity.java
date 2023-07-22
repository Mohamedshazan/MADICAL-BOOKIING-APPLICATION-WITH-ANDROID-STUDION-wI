package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WalcomeActivity extends AppCompatActivity {

    CardView card1;
    CardView card2;

//    ImageView img1;
//    ImageView img2;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walcome);

       card1=findViewById(R.id.cardWalcome);
       card2=findViewById(R.id.cardWalcome1);
//       img1=findViewById(R.id.imageView6);
//       img2=findViewById(R.id.imageView7);



        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(WalcomeActivity.this,LoginActivity.class));

            }

        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(WalcomeActivity.this,AdminLoginActivity.class));
            }

        });

//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(WalcomeActivity.this,SensorActivity.class));
//
//            }
//
//        });
//
//        img2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(WalcomeActivity.this,SensorActivity.class));
//            }
//
//        });
    }
}