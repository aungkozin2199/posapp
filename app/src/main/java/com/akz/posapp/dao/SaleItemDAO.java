package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.SaleItemModel;

public class SaleItemDAO {
    public String
                TBName="SaleItem",
                ColId="Id",
                ColSr="Sr",
                ColItemId="ItemId",
                ColSPrice="SPrice",
                ColOPrice="OPrice",
                ColOderId="OrderId",
                ColQty="Qty";
    DBHelper dbHelper;
    SQLiteDatabase db;
    public SaleItemDAO(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long saveModel(SaleItemModel model){
        ContentValues values=new ContentValues();
        values.put(ColItemId,model.ItemId);
        values.put(ColOPrice,model.OPrice);
        values.put(ColSPrice,model.SPrice);
        values.put(ColSr,model.Sr);
        values.put(ColOderId,model.OrderId);
        values.put(ColQty,model.Qty);
        long id=db.insert(TBName,null,values);
        return id;

    }

}
