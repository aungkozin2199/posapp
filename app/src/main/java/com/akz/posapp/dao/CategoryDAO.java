package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.CategoryModel;
import com.akz.posapp.model.UnitModel;

import java.util.ArrayList;

public class CategoryDAO {
    public static String
            ColId="Id",
            ColName="Name",
            ColColor="ColorId",
            TBName="Category";

    DBHelper dbHelper;
    SQLiteDatabase db;
    public CategoryDAO(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long saveModel(CategoryModel model){
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColor,model.ColorId);
        long id=db.insert(TBName,null,values);
        return id;
    }

    public ArrayList<CategoryModel> getModels() {
        ArrayList<CategoryModel> categoryModels=new ArrayList<CategoryModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext()){
            CategoryModel tmp=new CategoryModel();
            tmp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            tmp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColor));
            tmp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            categoryModels.add(tmp);
        }
        return categoryModels;

    }
}
