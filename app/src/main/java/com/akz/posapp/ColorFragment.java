package com.akz.posapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akz.posapp.adapter.ColorAdapter;
import com.akz.posapp.dao.ColorDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {


   static private View v;
    static private RecyclerView colordata;

    public ColorFragment() {
        // Required empty public constructor
    }
    public static int selectedposition=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_color, container, false);
        loadColor();
        return v;
    }
    public void loadColor(){
        colordata=(RecyclerView)v.findViewById(R.id.colordata);
        ColorDAO colorDAO=new ColorDAO(getContext());
        ColorAdapter adapter=new ColorAdapter(colorDAO.getModel(),getContext(),getResources().getString(R.string.item_frag));
        colordata.setAdapter(adapter);
        GridLayoutManager gm=new GridLayoutManager(getContext(),3);
        colordata.setLayoutManager(gm);
    }
        public static void removecheck(){
        for (int i=0;i<colordata.getAdapter().getItemCount();i++){
            if (i!=selectedposition){
                View view=colordata.getChildAt(i);
                view.findViewById(R.id.checkcolor).setVisibility(View.GONE);
            }
        }
        }

}
