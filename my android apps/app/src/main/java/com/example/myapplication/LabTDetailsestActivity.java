package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTDetailsestActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnAddToCart,btnBack;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_tdetailsest);

        tvPackageName=findViewById(R.id.textViewLTDTitle);
        tvTotalCost=findViewById(R.id.textViewBMDTotalCost);
        edDetails=findViewById(R.id.editTextLTDTextMultiline);
        btnAddToCart=findViewById(R.id.buttonBMDAddTOCard);
        btnBack=findViewById(R.id.buttonBMCardBack);


        edDetails.setKeyListener(null);
        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total cost:"+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTDetailsestActivity.this,LabTestActivity.class));
            }
        });


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"myapp1",null,1) ;

                if(db.checkCard(username,product)==1){
                    Toast.makeText(getApplicationContext(),"product alrdey added",Toast.LENGTH_SHORT).show();
                }else {

                    db.addCard(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Recode inserted To the Card",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTDetailsestActivity.this,LabTestActivity.class));
                }
            }
        });

    }
}