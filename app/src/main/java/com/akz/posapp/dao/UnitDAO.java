package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.UnitModel;
import com.akz.posapp.model.UnitModel;

import java.util.ArrayList;
import java.util.Collection;

public class UnitDAO {
    public static String
            ColId="Id",
            ColName="Name",
            ColColor="ColorId",
            TBName="Unit";

        DBHelper dbHelper;
       static SQLiteDatabase db;
        public UnitDAO(Context context){
            dbHelper=new DBHelper(context);
            db=dbHelper.getWritableDatabase();
        }

    public  ArrayList<UnitModel> getModels() {
        ArrayList<UnitModel> UnitModels=new ArrayList<UnitModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext()){
            UnitModel tmp=new UnitModel();
            tmp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            tmp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColor));
            tmp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            UnitModels.add(tmp);
        }
        return UnitModels;
    }

    public long saveModel(UnitModel model){
            ContentValues values=new ContentValues();
            values.put(ColName,model.Name);
            values.put(ColColor,model.ColorId);
            long id=db.insert(TBName,null,values);
            return id;
        }
}
