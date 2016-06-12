package com.example.yogendra19.wi_fi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private BroadcastReceiver wifireciever;
    private ArrayAdapter adapter;
    Switch btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2);
        listview.setAdapter(adapter);
        wifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
        wifireciever = new WiFiScanReceiver();

        btn = (Switch) findViewById(R.id.switch1);
        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    toggleWiFi(true);
                    Toast.makeText(getApplicationContext(), "Wi-Fi Enabled!", Toast.LENGTH_LONG).show();

                } else {
                    toggleWiFi(false);
                    Toast.makeText(getApplicationContext(), "Wi-Fi Disabled!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void toggleWiFi(boolean status){
       // WifiManager wifiManager = (WifiManager)this.getSystemService(WIFI_SERVICE);
        if (status && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (!status && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    class WiFiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                List<ScanResult> wifiScanResultList = wifiManager.getScanResults();
                for(int i = 0; i < wifiScanResultList.size(); i++){
                    ScanResult accessPoint = wifiScanResultList.get(i);
                    String listItem = accessPoint.SSID+", "+accessPoint.BSSID+", "+accessPoint.capabilities;
                    adapter.add(listItem);
                }
            }
        }
    }


    protected void onResume() {
        super.onResume();
        // Register the BroadcastReceiver for SCAN_RESULTS_AVAILABLE_ACTION
        IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifireciever, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifireciever);
    }





}
