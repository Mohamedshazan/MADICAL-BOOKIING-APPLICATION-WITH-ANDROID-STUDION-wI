package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    EditText edUsername, edPassword, edEmail,edConfirm;
    Button btn;
    TextView Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername =findViewById(R.id.editTextAppFullName);
        edPassword =findViewById(R.id.editTextAppContactNumber);
        edEmail =findViewById(R.id.editTextAppAddress);
        edConfirm =findViewById(R.id.editTextAppFees);
        btn=findViewById(R.id.buttonRegister);
        Tv=findViewById(R.id.textViewExistingUser);

        Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, AdminLoginActivity.class));
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username=edUsername.getText().toString();
                String Email=edEmail.getText().toString();
                String Password=edPassword.getText().toString();
                String conform=edConfirm.getText().toString();
                Database db=new Database(getApplicationContext(),"myapp1",null,1);

                if (Username.length()==0 ||Email.length()==0|| Password.length()==0|| conform.length()==0)  {

                    Toast.makeText(getApplicationContext(), "please File all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Password.compareTo(conform)==0){
                        if(isValid(Password)){
                            db.register(Username,Email,Password);
                            Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(RegisterActivity.this, AdminLoginActivity.class));
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Password and conform password didn't match", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password must contain at least 8 curracctor,having letters and degit and specal symbol", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });

    }
    public static boolean isValid(String passwordhere){
      int  f1=0,f2=0,f3=0;
      if(passwordhere.length()<8){
          return false;
      }else {
          for(int p=0 ; p < passwordhere.length() ;p++){
              if(Character.isLetter(passwordhere.charAt(p))){
                  f1=1;
              }
          }
          for(int r=0 ; r < passwordhere.length() ;r++){
              if(Character.isDigit(passwordhere.charAt(r))){
                  f2=1;
              }
          }

          for(int s=0 ; s < passwordhere.length() ;s++){
              char c=passwordhere.charAt(s);
              if(c>=33&&c<=46||c==64){
                  f3=1;
              }
          }
          if (f1==1 && f2==1 && f3==1)
              return true;
          return false;

      }

    }

}