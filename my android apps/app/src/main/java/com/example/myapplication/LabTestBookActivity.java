package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontactnumber,edpincode;
    Button btnTBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edcontactnumber = findViewById(R.id.editTextLTBContactNumber);
        edpincode = findViewById(R.id.editTextLTBPincode);
        btnTBooking = findViewById(R.id.buttonLTBBooking);

        btnTBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                try {
                    Intent intent = getIntent();
                    String priceString = intent.getStringExtra("price");
                    String date = intent.getStringExtra("Date");
                    String time = intent.getStringExtra("Time");

                    if (priceString != null && date != null && time != null) {
                        String[] price = priceString.split(":");
                        if (price.length > 1) {
                            // Remove the equal sign (=) from the price string
                            String priceValueString = price[1].replace("=", "");

                            // Parse the float value
                            float priceValue = Float.parseFloat(priceValueString);

                            Database db = new Database(getApplicationContext(), "myapp1", null, 1);
                            db.Addorder(username, edname.getText().toString(), edaddress.getText().toString(), edcontactnumber.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date, time, priceValue, "lab");
                            db.RemoveCard(username, "lab");

                            Toast.makeText(getApplicationContext(), "Your Booking is done Successfully ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "There was an error with your booking", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "There was an error with your booking", Toast.LENGTH_LONG).show();
                    }
                } catch (NullPointerException e) {
                    Log.e("LabTestBookActivity", "NullPointerException: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "There was an error with your booking", Toast.LENGTH_LONG).show();
                }

            }
    });}}



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lab_test_book);
//
//        edname=findViewById(R.id.editTextLTBFullName);
//        edaddress=findViewById(R.id.editTextLTBAddress) ;
//        edcontactnumber=findViewById(R.id.editTextLTBContactNumber) ;
//        edpincode=findViewById(R.id.editTextLTBPincode) ;
//        btnTBooking=findViewById(R.id.buttonLTBBooking) ;
//
//
//        Intent intent=getIntent();
//        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
//        String date=intent.getStringExtra("date");
//        String time=intent.getStringExtra("time");
//
//
//
//
////                btnTBooking.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
////                        String username = sharedPreferences.getString("username", "");
////
////
////
////                Database db = new Database(getApplicationContext(), "myapp1", null, 1);
////                db.Addorder(username, edname.getText().toString(), edaddress.getText().toString(), edcontactnumber.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab");
////                db.RemoveCard(username, "lab");
////
////                Toast.makeText(getApplicationContext(), "Your Booking is done Successfully ", Toast.LENGTH_LONG).show();
////                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
////
////            }
////        });
//
//
//        btnTBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                String username = sharedPreferences.getString("username", "");
//
//                try {
//                    String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
//                    String date=intent.getStringExtra("date");
//                    String time=intent.getStringExtra("time");
//
//                    Database db = new Database(getApplicationContext(), "myapp1", null, 1);
//                    db.Addorder(username, edname.getText().toString(), edaddress.getText().toString(), edcontactnumber.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab");
//                    db.RemoveCard(username, "lab");
//
//                    Toast.makeText(getApplicationContext(), "Your Booking is done Successfully ", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
//                } catch (NullPointerException e) {
//                    Log.e("LabTestBookActivity", "NullPointerException: " + e.getMessage());
//                    Toast.makeText(getApplicationContext(), "There was an error with your booking", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }
//}
//
//
////
////package com.example.myapplication;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.content.Context;
////import android.content.Intent;
////import android.content.SharedPreferences;
////import android.os.Bundle;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.Toast;
////
////@SuppressWarnings("ALL")
////public class LabTestBookActivity extends AppCompatActivity {
////
////    EditText edname,edaddress,edcontactnumber,edpincode;
////    Button btnTBooking;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_lab_test_book);
////
////        edname=findViewById(R.id.editTextLTBFullName) ;
////        edaddress=findViewById(R.id.editTextLTBAddress) ;
////        edcontactnumber=findViewById(R.id.editTextLTBContactNumber) ;
////        edpincode=findViewById(R.id.editTextLTBPincode) ;
////        btnTBooking=findViewById(R.id.buttonLTBBooking) ;
////
////
////        Intent intent=getIntent();
////        String[] price=intent.getStringExtra("price").split(":");
////        String date=intent.getStringExtra("date");
////        String time=intent.getStringExtra("time");
////
////        btnTBooking.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
////                String username = sharedPreferences.getString("username", "");
////
////                if (edname.getText() == null || edname.getText().toString().isEmpty() ||
////                        edaddress.getText() == null || edaddress.getText().toString().isEmpty() ||
////                        edcontactnumber.getText() == null || edcontactnumber.getText().toString().isEmpty() ||
////                        edpincode.getText() == null || edpincode.getText().toString().isEmpty()) {
////                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
////                    return;
////                }
////
////                try {
////                    int pinCode = Integer.parseInt(edpincode.getText().toString());
////                } catch (NumberFormatException e) {
////                    Toast.makeText(getApplicationContext(), "Please enter a valid pincode", Toast.LENGTH_LONG).show();
////                    return;
////                }
////
////                Database db = new Database(getApplicationContext(), "myapp1", null, 1);
////                db.Addorder(username, edname.getText().toString(), edaddress.getText().toString(), edcontactnumber.getText().toString(),
////                        Integer.parseInt(edpincode.getText().toString()), date, time, Float.parseFloat(price[1]), "lab");
////                db.RemoveCard(username, "lab");
////
////                Toast.makeText(getApplicationContext(), "Your Booking is done Successfully ", Toast.LENGTH_LONG).show();
////                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
////            }
////        });
////    }
////}
//
