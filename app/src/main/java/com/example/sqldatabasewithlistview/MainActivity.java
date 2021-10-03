package com.example.sqldatabasewithlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userEditText , numberEditText;
    private Button saveBtn,showBtn,updateBtn,deleteBtn;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEditText=findViewById(R.id.userEditText);
        numberEditText=findViewById(R.id.numberEditText);
        saveBtn=findViewById(R.id.saveBtn);
        showBtn=findViewById(R.id.showBtn);
        updateBtn=findViewById(R.id.updateBtn);
        deleteBtn=findViewById(R.id.deleteBtn);
        saveBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase =databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        String username = userEditText.getText().toString();
        String mobileNumber = numberEditText.getText().toString();

        if (view.getId()==R.id.saveBtn){
            if (username.equals("")&&mobileNumber.equals("")){
                Toast.makeText(this, "plz enter valid data", Toast.LENGTH_SHORT).show();
            }else{
                long rowNumber = databaseHelper.saveData(mobileNumber,username);
                if (rowNumber > -1){
                    Toast.makeText(this, "save Successfully", Toast.LENGTH_SHORT).show();
                    //to make both edit text empty :)
                    userEditText.setText("");
                    numberEditText.setText("");
                }else{
                    Toast.makeText(this, "Data is not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(view.getId()==R.id.showBtn){
            Intent intent =new Intent(MainActivity.this,showActivity2.class);
                startActivity(intent);
        }else if(view.getId()==R.id.updateBtn){
            Boolean isUpdated = databaseHelper.updateData(mobileNumber,username);
            if(isUpdated==true){
                    Toast.makeText(this, "contact updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "update unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }else if(view.getId()==R.id.deleteBtn){
            int value = databaseHelper.deleteData(mobileNumber);
                if (value<0){
                    Toast.makeText(this, "contact not deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "contact is deleted", Toast.LENGTH_SHORT).show();
                }
        }
    }
}