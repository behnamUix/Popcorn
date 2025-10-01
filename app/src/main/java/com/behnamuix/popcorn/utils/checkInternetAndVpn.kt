package com.behnamuix.popcorn.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

fun checkNetAndVpn(ctx: Context): Boolean {
    val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return false
    val net = cm.activeNetwork ?: return false
    val caps = cm.getNetworkCapabilities(net) ?: return false

    val vpn = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    val internet = caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    return if (vpn && internet) {
        true
    } else {

        false
    }
}
