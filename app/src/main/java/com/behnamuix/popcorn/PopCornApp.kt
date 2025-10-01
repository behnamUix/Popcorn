package com.behnamuix.popcorn

import android.app.Application
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast
import com.behnamuix.popcorn.koin.appModule
import com.behnamuix.popcorn.utils.checkNetAndVpn
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PopCornApp : Application() {


    override fun onCreate() {
        super.onCreate()

        checkNetAndVpn(this)

        startKoin {
            androidContext(this@PopCornApp)
            modules(appModule)
        }


    }


}
