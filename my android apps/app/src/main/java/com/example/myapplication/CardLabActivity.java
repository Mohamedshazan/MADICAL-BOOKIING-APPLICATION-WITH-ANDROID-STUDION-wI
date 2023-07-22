package com.example.myapplication;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class CardLabActivity extends AppCompatActivity {


    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    TextView tvTotal;
    ListView lst;

    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private Button dateButton,timeButton,btnCheckout,btnBack;
    private String [][]packages={};


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_lab2);

        dateButton = findViewById(R.id.buttonLTCardDate);
        timeButton = findViewById(R.id.buttonLTCardTime);
        btnCheckout = findViewById(R.id.buttonLTCardChackeout);
        btnBack = findViewById(R.id.buttonLTCardBack);
        tvTotal = findViewById(R.id.textViewLTCardTotalCost);
        lst = findViewById(R.id.listviewLBCard);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "myapp1", null, 1);
        float totalAmount = 0;
        ArrayList dbData = db.getCard(username, "lab");
        Toast.makeText(getApplicationContext(), "" + dbData, Toast.LENGTH_LONG).show();

        packages = new String[dbData.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];

        }


        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "cost:" + strData[1] + "/=";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);

        }


        tvTotal.setText("Total cost:=" + totalAmount);

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);

        }


        sa = new SimpleAdapter(this, list,
                R.layout.multi_line,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardLabActivity.this, LabTestActivity.class));
            }


        });


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CardLabActivity.this, LabTestBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("Date", dateButton.getText());
                it.putExtra("Time", timeButton.getText());
                startActivity(it);
            }
        });




        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                datePickerDialog.show();
            }
        });

        //time picker

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


    }

    public void initDatePicker() {
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

    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }


        };

        Calendar cal= Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog =new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }

    }


