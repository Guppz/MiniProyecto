package com.example.fran.miniproyecto.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.fran.miniproyecto.Fragmentos.*;

public class Adaptador extends FragmentPagerAdapter {
    BatteryFragment bF;
    WifiFragment wF;
    GpsFragment gF;
    public Adaptador(FragmentManager fm) {
        super(fm);
        bF = new BatteryFragment();
        wF = new WifiFragment();
        gF = new GpsFragment();

    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return bF;
        }else if (i == 1){
            return wF;
        }else {
            return gF;
        }
    }

    @Override
    public int getCount(){return 3;}

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Battery";
        }else if (position == 1){
            return "Wifi";
        }else {
            return "GPS";
        }
    }

}
