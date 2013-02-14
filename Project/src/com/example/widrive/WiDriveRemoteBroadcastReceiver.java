package com.example.widrive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.util.Log;

public class WiDriveRemoteBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager rManager;
    private Channel rChannel;
    private RemoteActivity rActivity;

	
    public WiDriveRemoteBroadcastReceiver(WifiP2pManager manager, Channel channel, RemoteActivity activity) {
        super();
        this.rManager = manager;
        this.rChannel = channel;
        this.rActivity = activity;
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi Direct is enabled
            	Log.d(WiDriveActivity.TAG, "WiFi Direct is enabled");
            	rActivity.setIsWifiP2pEnabled(true);
            } else {
                // Wi-Fi Direct is not enabled
            	Log.d(WiDriveActivity.TAG, "WiFi Direct is disabled");
            	rActivity.setIsWifiP2pEnabled(false);
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }
}
