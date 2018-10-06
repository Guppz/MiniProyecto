package com.example.fran.miniproyecto.Fragmentos;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fran.miniproyecto.MainActivity;
import com.example.fran.miniproyecto.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.WIFI_SERVICE;

public class WifiFragment extends Fragment {
    @BindView(R.id.info2)
    TextView info2;

    @BindView(R.id.info3)
    TextView info3;
    @BindView(R.id.list)
    GridView list;

    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1001;

    private ArrayAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.wifi_fragment, container, false);
        ButterKnife.bind(this, view);
        checkPermissions();
        loadWifiSection();
        return view;
    }

    private void loadWifiSection() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        getActivity().registerReceiver(batteryInfoReceiver, intentFilter);

    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateWifiData(intent);
        }
    };

    private void updateWifiData(Intent intent) {


        final String action = intent.getAction();
        WifiManager wifiManager = (WifiManager)getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        List<ScanResult> scanResults = wifiManager.getScanResults();
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        int wifiState = wifiManager.getWifiState();
        int wifiConection=1;
        String dhcp = dhcpInfo.toString();
        switch (wifiState){
            case 1:wifiConection=R.string.wifi_disconnected;
            break;
            case 2:wifiConection=R.string.wifi_conecting;
            break;
            case 3:wifiConection=R.string.wifi_conected;
            break;
        }
        info2.setText("Estado: "+ getString(wifiConection));
        info3.setText(dhcp);
        adapter(scanResults);


    }

    public void checkPermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
        }
    }


    public void adapter(final List<ScanResult>listScan){
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
                name.setText(listScan.get(position).SSID);
                return row;
            }
        });
    }




}
