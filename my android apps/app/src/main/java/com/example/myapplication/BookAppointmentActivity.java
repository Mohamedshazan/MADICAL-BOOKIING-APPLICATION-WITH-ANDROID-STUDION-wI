package com.example.myapplication;//package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView Tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    public Button dateButton,timeButton,btnBook,btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);

        dateButton=findViewById(R.id.buttonAppDate);
        timeButton=findViewById(R.id.buttonAppTime);

        btnBook=findViewById(R.id.ButtonBookAppointment);
        btnBack=findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String FullName = it.getStringExtra("text2");
        String Address = it.getStringExtra("text3");
        String Contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        Tv.setText(title);
        ed1.setText(FullName);
        ed2.setText(Address);
        ed3.setText(Contact);
        ed4.setText("cons fees:" + fees + "/-");


// date picker

        initDatepicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                datePickerDialog.show();
            }
        });

//     time picker

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


//Back button

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }

        });

 //Book Appointment Registration Button

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "myapp1", null, 1);
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                if (db.checkAppointmentExist(username,title+"=>"+FullName,Address,Contact,dateButton.getText().toString(),timeButton.getText().toString())==1) {

                    Toast.makeText(getApplicationContext(), "Your Appointment is done Succesfully ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.Addorder(username,title+"=>"+FullName,Address,Contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appoinment");

                    Toast.makeText(getApplicationContext(),"Your Appointment is done Succesfully ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });

    }








    private void initTimePicker() {

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }


        };

        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog =new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }


    private void initDatepicker() {

        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);


            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog =new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
}




