package com.akz.posapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akz.posapp.DBHelper;
import com.akz.posapp.model.ColorModel;

import java.util.ArrayList;

public class ColorDAO {
    public String ColId="Id",
                    ColRed="ColorRed",
                    ColGreen="ColorGreen",
                    ColBlue="ColorBlue",
                    TBName="Color";
    DBHelper dbHelper;
    SQLiteDatabase db;

    public ColorDAO(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
public long saveModel(ColorModel model){
    ContentValues values=new ContentValues();
    values.put(ColRed,model.ColorRed);
    values.put(ColGreen,model.ColorGreen);
    values.put(ColBlue,model.ColorBlue);
   long id=db.insert(TBName,null,values);
return id;
    }
    public ArrayList<ColorModel> getModel(){
        Cursor c= db.rawQuery(" select * from "+TBName,null);
            ArrayList<ColorModel> colorModels=new ArrayList<>();
        while (c.moveToNext()){
            ColorModel temp=new ColorModel();
            temp.Id=c.getInt(c.getColumnIndex(ColId));
            temp.ColorRed=c.getInt(c.getColumnIndex(ColRed));
            temp.ColorGreen=c.getInt(c.getColumnIndex(ColGreen));
            temp.ColorBlue=c.getInt(c.getColumnIndex(ColBlue));
            colorModels.add(temp);

        }
        return colorModels;
    }

    public ColorModel getModelById(int colorId) {
        Cursor c= db.rawQuery(" select * from "+TBName+" where "+ColId+"=?", new String[]{String.valueOf(colorId)});
        ArrayList<ColorModel> colorModels=new ArrayList<>();
        ColorModel temp=new ColorModel();

        while (c.moveToNext()){
            temp.Id=c.getInt(c.getColumnIndex(ColId));
            temp.ColorRed=c.getInt(c.getColumnIndex(ColRed));
            temp.ColorGreen=c.getInt(c.getColumnIndex(ColGreen));
            temp.ColorBlue=c.getInt(c.getColumnIndex(ColBlue));

        }
        return temp;
    }
}
