package com.andraganoid.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build

class ConnectivityState(private val context: Context) {

    private var networkCapabilities: NetworkCapabilities? = null
    private var connectivityManager: ConnectivityManager? = null

    fun setListeners() {
        val networkCallback: ConnectivityManager.NetworkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                connectivityStatus = true
                networkCapabilities =
                    connectivityManager?.getNetworkCapabilities(connectivityManager?.activeNetwork)!!
                if (networkCapabilities != null) {
                    netTransport = when {
                        networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
                        networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "CELLULAR"
                        else -> ""
                    }
                }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                connectivityStatus = false
                netTransport = ""
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                connectivityStatus = false
                netTransport = ""
            }
        }

        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager?.registerNetworkCallback(request, networkCallback)
        }
    }

    companion object {
        var connectivityStatus = false
        var netTransport: String = ""
    }

}