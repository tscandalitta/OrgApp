package com.example.proyectoandroidtdp.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.proyectoandroidtdp.R;

public class ActivityAyuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        finish();
        return true;
    }
}
