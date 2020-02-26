package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.BrandModel;
import com.akz.posapp.model.BrandModel;
import com.akz.posapp.model.BrandModel;
import com.akz.posapp.model.UnitModel;

import java.util.ArrayList;

public class BrandDAO {
    public static String
            ColId="Id",
            ColName="Name",
            ColColor="ColorId",
            TBName="Brand";

    DBHelper dbHelper;
    SQLiteDatabase db;
    public BrandDAO(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long saveModel(BrandModel model){
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColor,model.ColorId);
        long id=db.insert(TBName,null,values);
        return id;
    }

    public ArrayList<BrandModel> getModels() {
        ArrayList<BrandModel> BrandModels=new ArrayList<BrandModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext()){
            BrandModel tmp=new BrandModel();
            tmp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            tmp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColor));
            tmp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            BrandModels.add(tmp);
        }
        return BrandModels;
    }
}
