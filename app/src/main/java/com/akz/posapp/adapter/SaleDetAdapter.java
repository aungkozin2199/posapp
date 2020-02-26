package com.akz.posapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akz.posapp.R;
import com.akz.posapp.dao.ItemDAO;
import com.akz.posapp.model.ItemModel;
import com.akz.posapp.model.SaleItemModel;

import java.util.ArrayList;

public class SaleDetAdapter extends RecyclerView.Adapter<SaleDetAdapter.SaleDetHolder>{
   ArrayList<SaleItemModel>saleItemModels=new ArrayList<>();
   Context context;

    public SaleDetAdapter(ArrayList<SaleItemModel> saleItemModels,Context context) {
        this.saleItemModels = saleItemModels;
        this.context=context;
    }

    @NonNull
    @Override
    public SaleDetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.saledet,parent,false);
        return new SaleDetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleDetHolder holder, int position) {
            holder.txtsr.setText(saleItemModels.get(position).Sr+"");
            holder.qty.setText(saleItemModels.get(position).Qty+"");
        ItemDAO itemDAO=new ItemDAO(context);
        ItemModel itemModel=itemDAO.getModelById(saleItemModels.get(position).ItemId);
        holder.txtname.setText(itemModel.Name+"");
    }

    @Override
    public int getItemCount() {
        return saleItemModels.size();
    }

    public class SaleDetHolder extends RecyclerView.ViewHolder{
        TextView txtsr,txtname;
        EditText qty;
        public SaleDetHolder(@NonNull View itemView) {
            super(itemView);
            txtsr=itemView.findViewById(R.id.sr);
            txtname=itemView.findViewById(R.id.name);
            qty=itemView.findViewById(R.id.itemqty);
        }
    }
}
