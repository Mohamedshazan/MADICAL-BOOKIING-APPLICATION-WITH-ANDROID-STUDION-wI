package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {


    TextView tvpackageName,tvTotalcose;
    EditText edDetails;
    Button btnBack,btnAddToCard,btnChoos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvpackageName=findViewById(R.id.textViewBMDTitle);
        edDetails=findViewById(R.id.editTextBMDTextMultiline);
        edDetails.setKeyListener(null);
        tvTotalcose=findViewById(R.id.textViewBMDTotalCost);
        btnBack=findViewById(R.id.buttonBMDBack);
        btnAddToCard=findViewById(R.id.buttonBMDAddTOCard);
        btnChoos=findViewById(R.id.buttonBMDchoos);

       Intent intent=getIntent();
       tvpackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalcose.setText("Total coste:"+intent.getStringExtra("text3")+"/=");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,buymedicineActivity.class));
            }
        });

        btnChoos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = 37.7749; // example latitude
                double longitude = -122.4194; // example longitude
                String uri = "http://maps.google.com/maps?q=" +latitude + "," + longitude;
                String uriString;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString = "http://maps.google.com/maps?q=37.7749,-122.4194")
);
                startActivity(intent);
            }
        });


        btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvpackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"myapp1",null,1) ;

                if(db.checkCard(username,product)==1){
                    Toast.makeText(getApplicationContext(),"product alrdey added",Toast.LENGTH_SHORT).show();
                }else {
                    db.addCard(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Recode inserted To the Card",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,buymedicineActivity.class));
                }
            }
        });
    }
}