package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] docotr_details1={
            {"Doctor name:shaan","Hospital Address:kalmunai","Exp:2years","MobileNumber:0752668412","600"},
            {"Doctor name:daan","Hospital Address:nntvur","Exp:5years","MobileNumber:0752668412","900"},
            {"Doctor name:asky","Hospital Address:kandy","Exp:8years","MobileNumber:0752668412","600"},
            {"Doctor name:isak","Hospital Address:colmbo","Exp:1years:","MobileNumber:0752668412","700"},
            {"Doctor name:aska","Hospital Address:nuwara","Exp:9years","MobileNumber:0752668412","600"},

    };

    private String[][] docotr_details2={
            {"Doctor name:shaan","Hospital Address:kalmunai","Exp:2years","MobileNumber:0752668412","600"},
            {"Doctor name:daan","Hospital Address:nntvur","Exp:5years","MobileNumber:0752668412","900"},
            {"Doctor name:asky","Hospital Address:kandy","Exp:8years","MobileNumber:0752668412","600"},
            {"Doctor name:isak","Hospital Address:colmbo","Exp:1years:","MobileNumber:0752668412","700"},
            {"Doctor name:aska","Hospital Address:nuwara","Exp:9years","MobileNumber:0752668412","600"},

    };

    private String[][] docotr_details3={
            {"Doctor name:shaan","Hospital Address:kalmunai","Exp:2years","MobileNumber:0752668412","600"},
            {"Doctor name:daan","Hospital Address:nntvur","Exp:5years","MobileNumber:0752668412","900"},
            {"Doctor name:asky","Hospital Address:kandy","Exp:8years","MobileNumber:0752668412","600"},
            {"Doctor name:isak","Hospital Address:colmbo","Exp:1years:","MobileNumber:0752668412","700"},
            {"Doctor name:aska","Hospital Address:nuwara","Exp:9years","MobileNumber:0752668412","600"},

    };

    private String[][] docotr_details4={
            {"Doctor name:shaan","Hospital Address:kalmunai","Exp:2years","MobileNumber:0752668412","600"},
            {"Doctor name:daan","Hospital Address:nntvur","Exp:5years","MobileNumber:0752668412","900"},
            {"Doctor name:asky","Hospital Address:kandy","Exp:8years","MobileNumber:0752668412","600"},
            {"Doctor name:isak","Hospital Address:colmbo","Exp:1years:","MobileNumber:0752668412","700"},
            {"Doctor name:aska","Hospital Address:nuwara","Exp:9years","MobileNumber:0752668412","600"},

    };

    private String[][] docotr_details5={
            {"Doctor name:shaan","Hospital Address:kalmunai","Exp:2years","MobileNumber:0752668412","600"},
            {"Doctor name:daan","Hospital Address:nntvur","Exp:5years","MobileNumber:0752668412","900"},
            {"Doctor name:asky","Hospital Address:kandy","Exp:8years","MobileNumber:0752668412","600"},
            {"Doctor name:isak","Hospital Address:colmbo","Exp:1years:","MobileNumber:0752668412","700"},
            {"Doctor name:aska","Hospital Address:nuwara","Exp:9years","MobileNumber:0752668412","600"},

    };

    TextView Tv;
    Button Bt;
    String[][] doctor_deatils={};
    HashMap<String,String> item;
    ArrayList List;
    SimpleAdapter sa;


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_deatails);

        Tv=findViewById(R.id.textViewDDTitle);
        Bt=findViewById(R.id.buttonBMGoToCard);
        Intent it=getIntent();
        String Title=it.getStringExtra("Title");
        Tv.setText(Title);

        if (Title.compareTo("Family physician")==0)
            doctor_deatils=docotr_details1;
        else
        if (Title.compareTo("Dietician")==0)
            doctor_deatils=docotr_details2;
        else
        if (Title.compareTo("Dentist")==0)
            doctor_deatils=docotr_details3;
        else

        if (Title.compareTo("Surgeon")==0)
            doctor_deatils=docotr_details4;
        else
            doctor_deatils=docotr_details5;



            Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

List =new ArrayList();
for (int i=0;i<doctor_deatils.length;i++){
     item=new HashMap<String,String>();
     item.put("line1",doctor_deatils[i][0]);
    item.put("line2",doctor_deatils[i][1]);
    item.put("line3",doctor_deatils[i][2]);
    item.put("line4",doctor_deatils[i][3]);
    item.put("line5","cond fees:"+doctor_deatils[i][4]+"/-");
    List.add(item);

    }
sa=new SimpleAdapter(this,List,
        R.layout.multi_line,
        new String[]{"line1","line2","line3","line4","line5"},
        new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        ListView list=findViewById(R.id.listViewDD);
        list.setAdapter(sa);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",Title);
                it.putExtra("text2",doctor_deatils[i][0]);
                it.putExtra("text3",doctor_deatils[i][1]);
                it.putExtra("text4",doctor_deatils[i][3]);
                it.putExtra("text5",doctor_deatils[i][4]);
                startActivity(it);

            }
        });


}}