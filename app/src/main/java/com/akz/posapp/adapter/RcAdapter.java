package com.akz.posapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akz.posapp.R;
import com.akz.posapp.SaleFragment;
import com.akz.posapp.dao.ColorDAO;
import com.akz.posapp.model.BrandModel;
import com.akz.posapp.model.CategoryModel;
import com.akz.posapp.model.ColorModel;
import com.akz.posapp.model.ItemModel;
import com.akz.posapp.model.UnitModel;

import java.util.ArrayList;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.RcHolder> {
    ArrayList<CategoryModel>categoryModels=new ArrayList<>();
    ArrayList<BrandModel>brandModels=new ArrayList<>();
    ArrayList<UnitModel>unitModels=new ArrayList<>();
    ArrayList<ItemModel>itemModels=new ArrayList<>();


    Context context;
    String fragName;
    public RcAdapter(Object[]objects, Context context,String fragName) {
            if (fragName.equals(context.getResources().getString(R.string.category_frag))){
                for (Object o: objects) {
                    categoryModels.add((CategoryModel) o);
                }            }
            if (fragName.equals(context.getResources().getString(R.string.brand_frag))){
                for (Object o: objects) {
                    brandModels.add((BrandModel)o);
                }
            }
            if (fragName.equals(context.getResources().getString(R.string.unit_frag))){
                for (Object o:objects){
                unitModels.add((UnitModel)o);
                }
            }
            if (fragName.equals(context.getResources().getString(R.string.item_frag)))
        {
            for (Object O: objects){
                itemModels.add((ItemModel)O);
            }
        }
    this.context = context;
        this.fragName=fragName;
    }

    @NonNull
    @Override
    public RcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,parent,false);
        return new RcHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcHolder holder, final int position) {
    holder.sr.setText(position+1+"");
   if (fragName.equals(context.getResources().getString(R.string.category_frag))){
       holder.name.setText(categoryModels.get(position).Name);
       ColorDAO colorDAO=new ColorDAO(context);
       ColorModel tmp=colorDAO.getModelById(categoryModels.get(position).ColorId);
       int colorcode= Color.argb(255,tmp.getColorRed(),tmp.getColorGreen(),tmp.getColorBlue());
       holder.coloritem.setBackgroundColor(colorcode);
   }
   if (fragName.equals(context.getResources().getString(R.string.brand_frag))){
       holder.name.setText(brandModels.get(position).Name);
       ColorDAO colorDAO=new ColorDAO(context);
       ColorModel tmp=colorDAO.getModelById(brandModels.get(position).ColorId);
       int colorcode= Color.argb(255,tmp.getColorRed(),tmp.getColorGreen(),tmp.getColorBlue());
       holder.coloritem.setBackgroundColor(colorcode);
   }
   if (fragName.equals(context.getResources().getString(R.string.unit_frag))){
       holder.name.setText(unitModels.get(position).Name);
       ColorDAO colorDAO=new ColorDAO(context);
       ColorModel tmp=colorDAO.getModelById(unitModels.get(position).ColorId);
       int colorcode= Color.argb(255,tmp.getColorRed(),tmp.getColorGreen(),tmp.getColorBlue());
       holder.coloritem.setBackgroundColor(colorcode);
   }
        if (fragName.equals(context.getResources().getString(R.string.item_frag))){
            holder.name.setText(itemModels.get(position).Name);
            ColorDAO colorDAO=new ColorDAO(context);
            ColorModel tmp=colorDAO.getModelById(itemModels.get(position).ColorId);
            int colorcode= Color.argb(255,tmp.getColorRed(),tmp.getColorGreen(),tmp.getColorBlue());
            holder.coloritem.setBackgroundColor(colorcode);
            holder.coloritem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SaleFragment.insertSaleItem(itemModels.get(position));
                }
            });
        }
   /*if (fragName.equals(context.getResources().getString(R.string.item_frag))){
       holder.name.setText(.get(position).Name);
       ColorDAO colorDAO=new ColorDAO(context);
       ColorModel tmp=colorDAO.getModelById(categoryModels.get(position).ColorId);
       int colorcode= Color.argb(255,tmp.getColorRed(),tmp.getColorGreen(),tmp.getColorBlue());
       holder.coloritem.setBackgroundColor(colorcode);
   }*/

    }

    @Override
    public int getItemCount() {
        if (fragName.equals(context.getResources().getString(R.string.category_frag))){
            return categoryModels.size();
        }
        if (fragName.equals(context.getResources().getString(R.string.brand_frag))){
            return brandModels.size();
        }
        if (fragName.equals(context.getResources().getString(R.string.unit_frag))){
            return unitModels.size();
        }
        if (fragName.equals(context.getResources().getString(R.string.item_frag))){
            return itemModels.size();
        }
        return 0;
    }

    public class RcHolder extends RecyclerView.ViewHolder{
        TextView sr,name,coloritem;
        public RcHolder(@NonNull View itemView) {
            super(itemView);
            sr=itemView.findViewById(R.id.sr);
            name=itemView.findViewById(R.id.name);
            coloritem=itemView.findViewById(R.id.coloritem);
        }
    }
}
