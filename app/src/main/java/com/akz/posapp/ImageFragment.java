package com.akz.posapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ImageFragment extends Fragment {

Button btncamera;
ImageView img;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_image, container, false);
    btncamera=view.findViewById(R.id.takephoto);
    img=view.findViewById(R.id.itemimage);
    btncamera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           checkPermission();
        }
    });
    return view;
    }
    public void checkPermission(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (getContext().checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED &&
                getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED&&
                getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                opencamera();
            }
            else{
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)&&shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)&&
                    shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Toast.makeText(getContext(),"Please Access Permission",Toast.LENGTH_LONG).show();
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},124);
            }
        }
        else {
            opencamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==124){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED&&
                grantResults[1]==PackageManager.PERMISSION_GRANTED&&
                grantResults[2]==PackageManager.PERMISSION_GRANTED){
                opencamera();
            }
        }
    }

    public static Uri imgur;
    private void opencamera() {
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.TITLE,"From Camera");
        imgur=getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIngent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIngent.putExtra(MediaStore.EXTRA_OUTPUT,imgur);
        startActivityForResult(cameraIngent,123);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==123){
            if (resultCode== AppCompatActivity.RESULT_OK){
                img.setImageURI(imgur);
            }
        }
    }
}
