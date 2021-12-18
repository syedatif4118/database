package com.example.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "Student_Table";
    public static final String col_1 = "ID";
    public static final String col_2 = "First_Name";
    public static final String col_3 = "Last_Name";
    public static final String col_4 = "Marks";
    private Object First_Name;

    public Databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,First_Name STRING," + "Last_Name STRING,Marks INTEGER)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public boolean inserData(String first_name,String last_name,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,first_name);
        contentValues.put(col_3,last_name);
        contentValues.put(col_4,marks);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else
        {

            return true;
        }
    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " +TABLE_NAME,null);
        return result;
    }
    public boolean updateData(String id, String first_name, String
            last_name,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,first_name);
        contentValues.put(col_3,last_name);
        contentValues.put(col_4,marks);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;
    }
}