package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private  String[][] packages={


            {"package 1-Full body checkup","","","","380"},
            {"package 2-Blood Glucose fasting","","","","299"},
            {"package 3-Covid19 antibody","","","","698"},
            {"package 4-Thyroid check","","","","425"},
            {"package 5-Thyroid check","","","","1000"}

    };



    private  String[] packages_details={
                     "Blood Glucose fasting\n"+
                     "Complete Homegram\n"+
                     "HbA1c\n"+
                     "Irn studeas\n"+
                     "kidny Function test\n"+
                     "LDH Lactate Dehydrogenase,Serualn\n"+
                     "lipit profile\n" +
                     "lever Function Test",
                   "Blood Glucose Fasting" ,
                   "COVID-19 Antibody - 196",
                   "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
                   "Complete Hemogras\n"+
                           "CRP (C Reactive Protein) Quantitative, Serus\n"+
                           "Iron Studies\n"+
                           "Kidney Function Te: Sod\n"+
                           "Vitanin 0 Total-25 Hydroxy\n"+
                           "Liver Fortisn Testi\n"+
                           "Lipid Profile"



};

    HashMap<String,String>item;
    ArrayList List;
    SimpleAdapter sa;
    Button btnGoToCard, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnGoToCard=findViewById(R.id.buttonBMGoToCard);
         btnBack=findViewById(R.id.buttonBMCardBack);
         listView=findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });


           List=new ArrayList();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            List.add(item);

        }

        sa=new SimpleAdapter(this,List,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}  );
              listView.setAdapter(sa);


              listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                      Intent it=new Intent(LabTestActivity.this,LabTDetailsestActivity.class);

                      it.putExtra("text1",packages[i][0]);
                      it.putExtra("text2",packages_details[i]);
                      it.putExtra("text3",packages[i][4]);
                      startActivity(it);

                  }
              });

        btnGoToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CardLabActivity.class));
            }
        });

  }
}