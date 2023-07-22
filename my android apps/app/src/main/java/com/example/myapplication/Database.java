package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2="create table card(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3="create table orderplace(username text,fullname text,address text,contactnumber text,pincode int,date text,time text,price float,otype text)";
        sqLiteDatabase.execSQL(qry3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username,String email,String password){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public  int login(String username,String password){
        int  result = 0;
        String Str[]=new String[2];
        Str[0]=username;
        Str[1]=password;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c= db.rawQuery("select * from users where username=? and password=?",Str);
        if (c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public void addCard(String username,String product,float price,String otype){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);

        SQLiteDatabase db=getWritableDatabase();
        db.insert("card",null,cv);
        db.close();
    }
    public  int checkCard(String username,String product){
        int  result = 0;
        String Str[]=new String[2];
        Str[0]=username;
        Str[1]=product;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c= db.rawQuery("select * from card where username=? and product=?",Str);
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    public void RemoveCard(String username,String otype){

        String Str[]=new String[2];
        Str[0]=username;
        Str[1]=otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("card","username=?and otype=?",Str);
        db.close();
    }


    public ArrayList getCard(String username, String otype){
        ArrayList <String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();

        String Str[]=new String[2];
        Str[0]=username;
        Str[1]=otype;
        Cursor c= db.rawQuery("select * from card where username=? and otype=?",Str);
        if (c.moveToFirst()){
            do{
                String product=c.getString(1);
                String price=c.getString(2);
                arr.add(product+"$"+price);

            }while (c.moveToNext());
        }

        db.close();
            return arr;
        }

        public void Addorder(String username, String fullname, String address, String contactnumber, int pincode, String date, String time, float price, String otype){
           ContentValues cv=new ContentValues();
            cv.put("username",username);
            cv.put("fullname",fullname);
            cv.put("address",address);
            cv.put("contactnumber",contactnumber);
            cv.put("pincode",pincode);
            cv.put("date",date);
            cv.put("time",time);
            cv.put("price",price);
            cv.put("otype",otype);
            SQLiteDatabase db=getWritableDatabase();
            db.insert(" orderplace",null,cv);
            db.close();




        }

    public ArrayList getorderdata(String username){
        ArrayList <String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();

        String Str[]=new String[1];
        Str[0]=username;

        Cursor c= db.rawQuery("select * from orderplace where username=?",Str);
        if (c.moveToFirst()){
            do{
                arr.add( c.getString(1)+"$"+ c.getString(2)+"$"+ c.getString(3)+"$"+ c.getString(4) +"$"+ c.getString(5)+"$"+ c.getString(6)+"$"+ c.getString(7)+"$+");



            }while (c.moveToNext());
        }

        db.close();
        return arr;
    }



    public int checkAppointmentExist(String username, String FullName, String Address, String contactnumber, String date,String time) {
        int result=0;
        String str[]=new String[6];
        str[0]=username;
        str[1]=FullName;
        str[2]=Address;
        str[3]=contactnumber;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db=getReadableDatabase();
//        Cursor c= db.rawQuery("select * from orderplace where username=? and FullName= ?and Address=? and contactnumber=? and date=? and time",str);
        @SuppressLint("Recycle") Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=? AND FullName=? AND Address=? AND contactnumber=? AND date=? AND time=?", str);

        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }
    }


