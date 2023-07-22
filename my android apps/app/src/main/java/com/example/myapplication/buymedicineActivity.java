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

public class buymedicineActivity extends AppCompatActivity {

private String[][]Packages ={

        {"Uprise-D3 1000IU Capsule", "","","","50"},

        {"HealthVit Chromium Picolinate 200mcg Capsule,","","","","305"},

                {"Vitamin B Complex Capsules", "", "", "","448"},

                {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","539"},

                        {"Dolo 650 Tablet","","","","30"},

                        {"Crocin 650 Advance Tablet","","","", "50"},

                        {"Strepsils Medicated Lozenges for Sore Throat","","","","40"},

                                {"Tata ing Calcium + Vitamin D3","","","","30"},

                                        {"Feronia -XT Tablet", "","","","130"}

    };


private String [] package_details={

        "building and keeping the bones & teeth strong\n" +
"Reducing Fatigue/stress and auscular pans\n"+
                "boosting maunity and increasing resistance against infection",
"Chroniua is an essential trace mineral that plays an isportant role in helping insulin peguls",
"Provides relief fro vitasin B deficiencies\n" +
               " Helps dn formation of red blood cells\n" +
"Haintains healthy nervous systen",
"It pronotes health as well as skin benefit.\n" +

"It helps reduce skin blemish and pigaentation.\n" +

"It act as safeguard the skin fros the harsh UVA and UVB sun rays.",
        "Bolg 650 Tablet helps relieve pain and fever by blocking the release of certain chemic",
"Helps relieve fever and bring down a high temperature\n" +

"Suitable for people with a heart condition or high blood pressure",
       " Relieves the syaptoss of a bacterial throat infection and soothes the recovery process/n .",

"Provides a mara and coafonting feeling during sore throat",
"Reduces the risk of calciua deficiency, Rickets, and Osteoporosis\n" +

"Proaotes mobility and flexibility of joints",
"Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"




    };



    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack,btnGoTOCard;
    ListView lst;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buymedicine);

      lst=findViewById(R.id.listViewBM);
      btnBack=findViewById(R.id.buttonBMBack);
      btnGoTOCard=findViewById(R.id.buttonBMGoToCard);



        btnGoTOCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(buymedicineActivity.this,CardBuyMedicineActivity.class));
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(buymedicineActivity.this,HomeActivity.class));
            }
        });

        list=new ArrayList();
        for (int i=0;i<Packages.length; i++){
            item=new HashMap<String, String>();
            item.put("line1", Packages[i][0]);
            item.put("line2", Packages[i][1]);
            item.put("line3", Packages[i][2]);
            item.put("line4", Packages[i][3]);
            item.put("line5", "Total coste:"+Packages[i][4]+"/=");
            list.add(item);

        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        lst.setAdapter(sa);

lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent it= new Intent(buymedicineActivity.this,BuyMedicineDetailsActivity.class);
        it.putExtra("text1",Packages[i][0]);
        it.putExtra("text2",package_details[i]);
        it.putExtra("text3",Packages[i][4]);
        startActivity(it);
    }
});


    }
}