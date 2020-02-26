package com.akz.posapp;

import android.os.Bundle;

import com.akz.posapp.dao.ColorDAO;
import com.akz.posapp.model.ColorModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        saveColor();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.unit_nav){
                    setFragment(new UnitFragment());
/*                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.nav_host_fragment,new UnitFragment());
                    ft.commit();*/
                }
                if (item.getItemId()==R.id.category_nav){
                    setFragment(new CategoryFragment());
                }
                if (item.getItemId()==R.id.brand_nav){
                    setFragment(new BrandFragment());
                }
                if (item.getItemId()==R.id.item_nav){
                    setFragment(new ItemFragment());
                }
                if (item.getItemId()==R.id.sale_nav){
                    setFragment(new SaleFragment());
                }
                drawer.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
    }
    public void setFragment(Fragment f){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,f);
        ft.commit();
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void saveColor(){
        ColorDAO dao=new ColorDAO(getApplicationContext());
        Random myrandom=new Random();
        if (dao.getModel().size()<=0) {

            for (int i = 0; i < 12; i++) {
                ColorModel temp = new ColorModel();
                temp.ColorRed = myrandom.nextInt(256);
                temp.ColorGreen = myrandom.nextInt(256);
                temp.ColorBlue = myrandom.nextInt(256);
                dao.saveModel(temp);

            }
        }
    }
}
