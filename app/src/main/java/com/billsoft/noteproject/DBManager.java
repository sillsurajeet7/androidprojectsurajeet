package com.billsoft.noteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private databasehelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

   public DBManager(Context c) {
       context= c;

   }  public DBManager  open() throws SQLException {
      dbHelper=new databasehelper(context);
      database = dbHelper.getWritableDatabase();
      return this;
    }
   public void close() {

       dbHelper.close();

   }
   public void insert(String name,  String desc){
       ContentValues contentValues = new ContentValues();
       contentValues.put(databasehelper.SUBJECT, name);
       contentValues.put(databasehelper.DESC, desc );
       database.insert(databasehelper.TABLE_NAME, null, contentValues );

   }
   public Cursor fetch(){
       String[] columns = new String[] {databasehelper._ID,
           databasehelper.SUBJECT,databasehelper.DESC};
        Cursor cursor = database.query(databasehelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
                 );
       if(cursor !=null){
           cursor.moveToFirst();
       } return  cursor;
   }
   public int update(long _id, String name, String desc){
       ContentValues contentValues = new ContentValues();
       contentValues.put(databasehelper.SUBJECT, name);
       contentValues.put(databasehelper.DESC, desc);

       int i =database.update(databasehelper.TABLE_NAME,
               contentValues, databasehelper._ID+
               " = " + _id, null);
       return i;

   }   public void delete(long _id){
        database.delete(databasehelper.TABLE_NAME,
                databasehelper._ID + " = " + _id, null);
    }
}
