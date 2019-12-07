package com.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.myapplication.DBHelper;
import com.myapplication.R;

public class MainActivity extends AppCompatActivity {
    EditText cn, vn, rd, al;
    DBHelper helper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        cursor = helper.getTable();
        cn = findViewById(R.id.et1);
        vn = findViewById(R.id.et2);
        rd = findViewById(R.id.et3);
        al = findViewById(R.id.et4);

    }

    public void add(View view) {
        String codeName = cn.getText().toString();
        String versionNumber = vn.getText().toString();
        String releaseDate = rd.getText().toString();
        String apiLevel = al.getText().toString();
        long insertData = helper.insert(codeName, versionNumber, releaseDate, apiLevel);
        if(insertData > 1){
            Toast.makeText(this, "successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "unsuccessful", Toast.LENGTH_LONG).show();
        }
    }

    public void first(View view) {
        try{
            Cursor cursor = helper.getTable();
            cursor.moveToFirst();
            cn.setText(cursor.getString(1));
            vn.setText(cursor.getString(2));
            rd.setText(cursor.getString(3));
            al.setText(cursor.getString(4));
        } catch (Exception ex){

        }
    }

    public void previous(View view) {
        try{
            Cursor cursor = helper.getTable();
            cursor.moveToPrevious();
            cn.setText(cursor.getString(1));
            vn.setText(cursor.getString(2));
            rd.setText(cursor.getString(3));
            al.setText(cursor.getString(4));
        } catch (Exception ex){

        }
    }

    public void clear(View view) {
        try{
            Cursor cursor = helper.getTable();
            cn.getText().clear();
            vn.getText().clear();
            rd.getText().clear();
            al.getText().clear();
        } catch (Exception ex){

        }
    }

    public void last(View view) {
        try{
            Cursor cursor = helper.getTable();
            cursor.moveToLast();
            cn.setText(cursor.getString(1));
            vn.setText(cursor.getString(2));
            rd.setText(cursor.getString(3));
            al.setText(cursor.getString(4));
        } catch (Exception ex){

        }
    }

    public void next(View view) {
        cursor.moveToNext();
        cn.setText(cursor.getString(1));
        vn.setText(cursor.getString(2));
        rd.setText(cursor.getString(3));
        al.setText(cursor.getString(4));
    }

    public void delete(View view) {
        String deleteR = cursor.getString(0);
        int deleteData = helper.delete(deleteR);
        if(deleteData > 0) {
            Toast.makeText(this, "successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "unsuccessful", Toast.LENGTH_LONG).show();
        }

    }

    public void update(View view) {
        String id = cursor.getString(0);
        String codeName = cn.getText().toString();
        String versionNumber = vn.getText().toString();
        String releaseDate = rd.getText().toString();
        String apiLevel = al.getText().toString();
        int updateData = helper.update(id, codeName, versionNumber, releaseDate, apiLevel);
        if(updateData == 1){
            Toast.makeText(this, "successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "unsuccessful", Toast.LENGTH_LONG).show();
        }
    }
    public void viewAll(View view){
        Intent i =new Intent(MainActivity.this,Main2Activity.class);
        startActivity(i);
    }
}
