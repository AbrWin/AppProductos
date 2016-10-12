package com.abrsoftware.appproductos.Utils.Connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by AbrWin on 12/10/16.
 */

public class Connection {

    public static boolean isOnline(Context mContex){
        ConnectivityManager cm = (ConnectivityManager)mContex.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

}
