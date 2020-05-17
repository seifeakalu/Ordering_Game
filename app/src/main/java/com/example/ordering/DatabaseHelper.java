package com.example.ordering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{

	public DatabaseHelper(Context context
			) {
		super(context, "Register.db",  null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("Create table score(level text primary key, collected text, time text)");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists score");
		
	}

	public boolean insert(int level , int collected){
		
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues contentValues= new ContentValues();
		contentValues.put("level",level);
		contentValues.put("collected",collected);
		long ins=db.insert("score",null, contentValues);
		
		if (ins==-1)return false;
				
		else return true;
		
		
		
	}
	
	
	public void update(int level , int collected){
		
		SQLiteDatabase db=this.getWritableDatabase();
		String query="UPDATE score SET level='"+level+ "' WHERE level!=0"; 
		db.execSQL(query);
		String query2="UPDATE score SET collected='"+collected+ "' WHERE collected!=0"; 
		db.execSQL(query2);
	
	}
	
	public Cursor alldata(){

		SQLiteDatabase db=this.getWritableDatabase();	
		
		Cursor cursor= db.rawQuery("select * from score ", null);
		return cursor;
		
		}
}
