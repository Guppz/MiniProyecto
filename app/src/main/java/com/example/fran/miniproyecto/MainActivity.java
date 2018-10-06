package com.example.fran.miniproyecto;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fran.miniproyecto.adapter.Adaptador;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager vp;
    @BindView(R.id.strip)
    PagerTabStrip pts;

    PagerAdapter pa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pa = new Adaptador(getSupportFragmentManager());
        vp.setAdapter(pa);
        vp.setOffscreenPageLimit(3);
    }

}
