package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.OrderModel;

public class OrderDAO {
    public String   TBName="OrderTable",
                    ColId="Id",
                    ColOrderNo="OrderNo",
                    ColSr="Sr",
                    ColTotal="Total",
                    ColDate="OrderDate",
                    ColCustomerId="CustomerId";
        DBHelper dbHelper;
        SQLiteDatabase db;
    public OrderDAO(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long saveModel(OrderModel model){
        ContentValues values=new ContentValues();
        values.put(ColOrderNo,model.OrderNo);
        values.put(ColCustomerId,model.CustomerId);
        values.put(ColSr,model.Sr);
        values.put(ColTotal,model.Total);
        values.put(ColDate,model.OrderDate);
        long id=db.insert(TBName,null,values);
        return  id;

    }
    public int getCount(String date){
       Cursor cursor= db.rawQuery("select count(*) from "+TBName+" where "+ColDate+"=?",new String[]{date});
    int count=0;
        while(cursor.moveToNext()){
        count=cursor.getInt(0);

    }
        return count;
    }
}
