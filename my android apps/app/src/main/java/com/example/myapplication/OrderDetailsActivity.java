package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][] orderDetails={};
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btn;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

    btn=findViewById(R.id.buttonHABack);
    lst=findViewById(R.id.listViewOD);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
        }
    });

        Database db=new Database(getApplicationContext(),"myapp1",null,1);
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        ArrayList dbData=db.getorderdata(username);

        orderDetails=new String[dbData.size()][];
        for(int i=0;i<orderDetails.length;i++){
            orderDetails[i]=new String[5];
            String arrData=dbData.get(i).toString();
            String[] strDate=arrData.split(java.util.regex.Pattern.quote("$"));
            orderDetails[i][0]=strDate[0];
            orderDetails[i][0]=strDate[1];
            if(strDate[7].compareTo("medicine")==0){
                orderDetails[i][3]="Del"+strDate[4];

            }else {
                orderDetails[i][3]="Del"+strDate[4]+""+strDate[5];

            }
            orderDetails[i][2]="Rs"+strDate[6];
            orderDetails[i][4]=strDate[7];
        }
        list=new ArrayList();
        for (int i=0;i<orderDetails.length; i++){
            item=new HashMap<String, String>();
            item.put("line1", orderDetails[i][0]);
            item.put("line2", orderDetails[i][1]);
            item.put("line3", orderDetails[i][2]);
            item.put("line4", orderDetails[i][3]);
            item.put("line5", orderDetails[i][4]);

            list.add(item);

        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}  );

        lst.setAdapter(sa);




    }
}