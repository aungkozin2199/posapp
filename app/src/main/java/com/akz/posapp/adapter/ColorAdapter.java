package com.akz.posapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akz.posapp.BrandFragment;
import com.akz.posapp.CategoryFragment;
import com.akz.posapp.ColorFragment;
import com.akz.posapp.ItemFragment;
import com.akz.posapp.R;
import com.akz.posapp.UnitFragment;
import com.akz.posapp.model.ColorModel;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder> {
    ArrayList<ColorModel> colorModels=new ArrayList<ColorModel>();
    Context context;
    String fragName;

    public ColorAdapter(ArrayList<ColorModel> colorModels, Context context,String fragName) {
        this.colorModels = colorModels;
        this.context = context;
        this.fragName=fragName;
    }


    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.coloritem,parent,false);
        return new ColorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorHolder holder, final int position) {
        final ColorModel temp=colorModels.get(position);
        final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
        holder.txtcoloritem.setBackgroundColor(colorcode);
        holder.txtcoloritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragName.equals(context.getResources().getString(R.string.category_frag))){
                    CategoryFragment.selectedcolorId=temp.Id;
                    CategoryFragment.selectedcolor.setBackgroundColor(colorcode);
                }
                if (fragName.equals(context.getResources().getString(R.string.brand_frag))){
                    BrandFragment.selectedcolorId=temp.Id;
                    BrandFragment.selectedcolor.setBackgroundColor(colorcode);
                }
                if (fragName.equals(context.getResources().getString(R.string.unit_frag))){
                    UnitFragment.selectedcolorId=temp.Id;
                    UnitFragment.selectedcolor.setBackgroundColor(colorcode);
                }
                if (fragName.equals(context.getResources().getString(R.string.item_frag))){
                    ItemFragment.selectedcolorId=temp.Id;
                    ColorFragment.selectedposition=position;
                    holder.checkcolor.setVisibility(View.VISIBLE);
                ColorFragment.removecheck();
                }
                }
        });



    }

    @Override
    public int getItemCount() {
        return colorModels.size();
    }

    public class ColorHolder extends RecyclerView.ViewHolder{
        TextView txtcoloritem;
        ImageView checkcolor;
        public ColorHolder(@NonNull View itemView) {
            super(itemView);
            txtcoloritem=itemView.findViewById(R.id.coloritem);
            checkcolor=itemView.findViewById(R.id.checkcolor);
        }
    }
}
