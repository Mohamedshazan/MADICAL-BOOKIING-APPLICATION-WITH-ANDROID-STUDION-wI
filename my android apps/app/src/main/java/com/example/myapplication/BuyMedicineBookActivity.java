package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontactnumber, edpincode;
    Button btnMBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontactnumber = findViewById(R.id.editTextBMBContactNumber);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnMBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String priceString = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");

        btnMBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                Database db = new Database(getApplicationContext(), "myapp1", null, 1);

                if (priceString != null && date != null) {
                    String[] price = priceString.split(":");
                    try {
                        float totalPrice = Float.parseFloat(price[1]);
                        int pincode = Integer.parseInt(edpincode.getText().toString());

                        db.Addorder(username, edname.getText().toString(), edaddress.getText().toString(), edcontactnumber.getText().toString(),
                                pincode, date, "", totalPrice, "medicine");
                        db.RemoveCard(username, "medicine");

                        Toast.makeText(getApplicationContext(), "Your Booking is done Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Invalid price format", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid intent data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}





//package com.example.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class BuyMedicineBookActivity extends AppCompatActivity {
//
//    EditText edname,edaddress,edcontactnumber,edpincode;
//    Button btnMBooking;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_buy_medicine_book);
//
//
//        edname=findViewById(R.id.editTextBMBFullName) ;
//        edaddress=findViewById(R.id.editTextBMBAddress) ;
//        edcontactnumber=findViewById(R.id.editTextBMBContactNumber) ;
//        edpincode=findViewById(R.id.editTextBMBPincode) ;
//        btnMBooking=findViewById(R.id.buttonBMBBooking) ;
//
//        Intent intent=getIntent();
//        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
//        String date=intent.getStringExtra("date");
//
//
////        btnMBooking.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
////                String username=sharedPreferences.getString("username","").toString();
////
////                Database db=new Database(getApplicationContext(),"myapp1",null,1);
////
////                    db.Addorder(username,edname.getText().toString(),edaddress.getText().toString(),edcontactnumber.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
////                    db.RemoveCard(username,"medicine");
////
////
////                Toast.makeText(getApplicationContext(),"Your Booking is done Successfully ",Toast.LENGTH_LONG).show();
////
////                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
////
////            }
////        });
//        btnMBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                String username = sharedPreferences.getString("username","");
//
//                Database db = new Database(getApplicationContext(), "myapp1", null, 1);
//
//                if (intent != null) {
//                    String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
//                    String date = intent.getStringExtra("date");
//
//                    db.Addorder(username, edname.getText().toString(), edaddress.getText().toString(), edcontactnumber.getText().toString(),
//                            Integer.parseInt(edpincode.getText().toString()), date, "", Float.parseFloat(price[1].toString()), "medicine");
//                    db.RemoveCard(username, "medicine");
//
//                    Toast.makeText(getApplicationContext(), "Your Booking is done Successfully ", Toast.LENGTH_LONG).show();
//
//                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//
//    }
//}


