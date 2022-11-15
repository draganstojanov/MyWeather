package com.andraganoid.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ConnectivityState(private val context: Context) : DefaultLifecycleObserver {

    companion object {
        var connectivityStatus = false
        var netTransport: String = ""
    }

    private var networkCapabilities: NetworkCapabilities? = null
    private var connectivityManager: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
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

    private fun setListeners() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager?.registerNetworkCallback(request, networkCallback)
        }
    }

    private fun removeListeners() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        setListeners()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        setListeners()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        removeListeners()
    }

}