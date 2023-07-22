package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class AdminLoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actiivity);

        edUsername =findViewById(R.id.editTextTextLoginUserName);
        edPassword =findViewById(R.id.editTextTextLoginPassword);
        btn=findViewById(R.id.buttonLogin);
        Tv=findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(view -> {

            String username= AdminLoginActivity.this.edUsername.getText().toString();
            String password= AdminLoginActivity.this.edPassword.getText().toString();
            Database db=new Database(getApplicationContext(),"myapp1",null,1);

            if (username.length()==0 || password.length()==0){

                Toast.makeText(getApplicationContext(), "please File all details", Toast.LENGTH_SHORT).show();
            }
            else {
                if(db.login(username,password)==1){
                    Toast.makeText(getApplicationContext(), "LogIn Success", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",username);
                    //to save our data with key and value
                    editor.apply();
                    startActivity(new Intent(AdminLoginActivity.this,OrderDetailsActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid username password", Toast.LENGTH_SHORT).show();
                }

            }

        });

        Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginActivity.this,RegisterActivity.class));
            }
        });


    }
}











