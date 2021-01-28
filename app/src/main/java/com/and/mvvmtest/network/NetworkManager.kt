package com.example.apitestproject.network

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlin.system.exitProcess

class NetworkManager {

    var mContext : Context? = null
    interface OnNetworkListener {
        fun networkAvailable()
        fun finishApp()
    }

    constructor(context: Context?) {
        mContext = context
    }


    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    fun checkNetworkAvailable(listener: OnNetworkListener) {
        if (isNetworkAvailable(mContext)) {
            listener.networkAvailable()
        } else {
            mContext?.let {

            }
        }
    }

}