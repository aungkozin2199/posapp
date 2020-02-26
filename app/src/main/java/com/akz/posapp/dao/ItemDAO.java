package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.ItemModel;

import java.util.ArrayList;

public class ItemDAO {
    public static String
            ColId="Id",
            ColName="Name",
            ColColor="ColorId",
            ColPicture="Picture",
            ColCategoryId="CategoryId",
            ColBrandId="BrandId",
            ColUnitId="UnitId",
            ColOriginalPrice="OriginalPrice",
            ColSalePrice="SalePrice",
            TBName="Item";

    DBHelper dbHelper;
    SQLiteDatabase db;
    public ItemDAO(Context context){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long saveModel(ItemModel model){
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColor,model.ColorId);
        values.put(ColPicture,model.Picture);
        values.put(ColCategoryId,model.CategoryId);
        values.put(ColBrandId,model.BrandId);
        values.put(ColUnitId,model.UnitId);
        values.put(ColOriginalPrice,model.OriginalPrice);
        values.put(ColSalePrice,model.SalePrice);
        long id=db.insert(TBName,null,values);
        return id;
    }
    public ArrayList<ItemModel>getModels(){
        Cursor c= db.rawQuery("select * from "+TBName,null);
        ArrayList<ItemModel>itemModels=new ArrayList<>();
        while (c.moveToNext()){
        ItemModel temp=new ItemModel();
        temp.Id=c.getInt(c.getColumnIndex(ColId));
        temp.Picture=c.getString(c.getColumnIndex(ColPicture));
        temp.ColorId=c.getInt(c.getColumnIndex(ColColor));
        temp.BrandId=c.getInt(c.getColumnIndex(ColBrandId));
        temp.CategoryId=c.getInt(c.getColumnIndex(ColCategoryId));
        temp.UnitId=c.getInt(c.getColumnIndex(ColUnitId));
        temp.Name=c.getString(c.getColumnIndex(ColName));
        temp.SalePrice=c.getInt(c.getColumnIndex(ColSalePrice));
        temp.OriginalPrice=c.getInt(c.getColumnIndex(ColOriginalPrice));
        itemModels.add(temp);
        }
        return itemModels;
    }

    public ItemModel getModelById(int Id){
        Cursor c= db.rawQuery("select * from "+TBName+ " where "+ColId+"=?",new String[]{String.valueOf(Id)});
        ItemModel temp=new ItemModel();
        while (c.moveToNext()){
        temp.Id=c.getInt(c.getColumnIndex(ColId));
        temp.Picture=c.getString(c.getColumnIndex(ColPicture));
        temp.ColorId=c.getInt(c.getColumnIndex(ColColor));
        temp.BrandId=c.getInt(c.getColumnIndex(ColBrandId));
        temp.CategoryId=c.getInt(c.getColumnIndex(ColCategoryId));
        temp.UnitId=c.getInt(c.getColumnIndex(ColUnitId));
        temp.Name=c.getString(c.getColumnIndex(ColName));
        temp.SalePrice=c.getInt(c.getColumnIndex(ColSalePrice));
        temp.OriginalPrice=c.getInt(c.getColumnIndex(ColOriginalPrice));
        }
        return temp;
    }
}
