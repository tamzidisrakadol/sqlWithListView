package com.example.sqldatabasewithlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class showActivity2 extends AppCompatActivity {
    private ListView contactListId;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show2);
        contactListId=findViewById(R.id.contactListId);
        databaseHelper=new DatabaseHelper(this);
        loadData();
    }
    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseHelper.showData();
        if (cursor.getCount()==0){
            Toast.makeText(this, "nothing to show", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listData.add(cursor.getString(1)+" \n "+cursor.getString(0));

            }
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.contactlist,R.id.nameText,listData);
        contactListId.setAdapter(adapter);
        contactListId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Selected value :  "+selectedValue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}