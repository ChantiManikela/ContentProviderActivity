package com.example.contentprovideractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG=MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()method called first time");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()method called ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()method called ");
    }

    public void onClickAddName(View view) {
        /**
         * Add the student data
         */
        Log.d(TAG,"AddName button pressed");
        ContentValues values= new ContentValues();
        values.put(CP_DatabaseExample.NAME,
                ((EditText)findViewById(R.id.editText1)).getText().toString());
        values.put(CP_DatabaseExample.GRADE,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        Uri uri = getContentResolver().insert(
                CP_DatabaseExample.CONTENT_URI, values);
        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
        Log.d(TAG,"uri is ::"+uri.toString());

    }


    @SuppressLint("Range")
    public void onClickRetrieveStudents(View view) {
        /**
         * Retrieve the StudentData
         *
         */
        Log.d(TAG,"Retrieve Students button pressed");
        String URL = "content://com.example.contentprovideractivity.CP_DatabaseExample";
        String selection= CP_DatabaseExample.NAME+((EditText)findViewById(R.id.editText1)).getText().toString()+"LIKE ?";
        String [] selectionArgs={"name"};
        Uri students = Uri.parse(URL);


        Cursor c =getContentResolver().query(students,null,selection,selectionArgs,"name");

        if (c.moveToFirst()) {
            Toast.makeText(this,
                    c.getString(c.getColumnIndex(CP_DatabaseExample._ID)) +
                            ", " +  c.getString(c.getColumnIndex( CP_DatabaseExample.NAME)) +
                            ", " + c.getString(c.getColumnIndex( CP_DatabaseExample.GRADE)),
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Retrieve Student Data"+c.getString(c.getColumnIndex(CP_DatabaseExample._ID)) +
                    ", " +  c.getString(c.getColumnIndex( CP_DatabaseExample.NAME)) +
                    ", " + c.getString(c.getColumnIndex( CP_DatabaseExample.GRADE)));

        }
    }

    public void onClockUpdateName(View v){
        Log.d(TAG,"update name Students button pressed");

        ContentValues updateValues= new ContentValues();
        String selectionClause = CP_DatabaseExample.NAME+((EditText)findViewById(R.id.editText1)).getText().toString()+  " LIKE ?";
        String[] selectionArgs = {"name"};
        int rowupdate=0;
        updateValues.put(CP_DatabaseExample.NAME, ((EditText)findViewById(R.id.editText1)).getText().toString());
        updateValues.put(CP_DatabaseExample.GRADE,((EditText)findViewById(R.id.editText2)).getText().toString());
        rowupdate = getContentResolver().update(CP_DatabaseExample.CONTENT_URI,updateValues,selectionClause,selectionArgs);
        Toast.makeText(this, String.valueOf(rowupdate), Toast.LENGTH_SHORT).show();
        Log.d(TAG,"number of rows updated ::"+ rowupdate);
    }

    public void onClickeDelete(View v){
        Log.d(TAG,"delete Students name  button pressed");
        String selectionClause = CP_DatabaseExample.NAME + ((EditText)findViewById(R.id.editText1)).getText().toString()+ " LIKE ?";
        String[] selectionArgs = {"name"};
        int rowdelete=0;
        rowdelete = getContentResolver().delete(CP_DatabaseExample.CONTENT_URI,selectionClause,selectionArgs);

        Toast.makeText(this,String.valueOf(rowdelete),Toast.LENGTH_LONG).show();
        Log.d(TAG,"number of rows in table  deteled ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() method called ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()method called ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()method called ");
    }
}