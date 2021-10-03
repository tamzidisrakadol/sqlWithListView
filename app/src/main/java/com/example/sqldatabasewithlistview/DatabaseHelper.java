package com.example.sqldatabasewithlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "contact.db";
    private static  final String TABLE_NAME = "contactList";
    private static  final String MOBILE_NUMBER= "number";
    private static  final String NAME = "name";
    private static final int VERSION_NUMBER = 11;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+MOBILE_NUMBER+" INTEGER PRIMARY KEY, "+NAME+" VARCHAR(255));";
    private Context context;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Exception  "+ e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch(Exception e){
            Toast.makeText(context, "Exception  "+ e, Toast.LENGTH_SHORT).show();
        }
    }
    public long saveData(String mobileNumber, String userName){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,userName);
        contentValues.put(MOBILE_NUMBER,mobileNumber);

        long rowNumber = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowNumber ;
    }
    public Cursor showData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
    public Boolean updateData(String mobileNumber,String userName){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,userName);
        contentValues.put(MOBILE_NUMBER,mobileNumber);
        sqLiteDatabase.update(TABLE_NAME,contentValues,MOBILE_NUMBER+" =?",new String[] {mobileNumber});
        return true;
    }
    public int deleteData(String mobileNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME,MOBILE_NUMBER+" =?",new String[]{mobileNumber});
        return value;
    }


}
