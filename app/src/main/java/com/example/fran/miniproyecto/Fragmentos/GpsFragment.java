package com.example.fran.miniproyecto.Fragmentos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fran.miniproyecto.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;
import static android.location.LocationManager.KEY_LOCATION_CHANGED;

public class GpsFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1001;
    @BindView(R.id.info2)
    TextView info2;
    @BindView(R.id.info3)
    TextView info3;
    @BindView(R.id.list)
    GridView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gps_fragment, container, false);
        ButterKnife.bind(this, v);
        getLocation(v);
        LocationManager locationManager = (LocationManager) v.getContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> proveedores = locationManager.getAllProviders();
        adapter(proveedores);
        return v;
    }

    public void getLocation(View v){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
        }
        LocationManager locationManager = (LocationManager) v.getContext().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        info2.setText("Su longitude es de : "+longitude);
        double latitude = location.getLatitude();
        info3.setText("Su latitude es de : "+latitude);

    }

    public void adapter(final List<String>listScan){
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listScan.size();
            }
            @Override
            public Object getItem(int position) {
                return listScan.get(position);
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = getLayoutInflater();
                View row = inflater.inflate(R.layout.text_layout, parent, false);
                TextView name;
                name= row.findViewById(R.id.listText);
                name.setText(listScan.get(position));
                return row;
            }
        });
    }



}
