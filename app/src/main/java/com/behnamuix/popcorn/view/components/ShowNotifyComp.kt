package com.behnamuix.popcorn.view.components

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.behnamuix.popcorn.R
import com.orhanobut.hawk.Hawk

@SuppressLint("MissingPermission")
@Composable
fun NotificationComp(ctx: Context) {
    var showNotif by remember { mutableStateOf(false) }

    val chanelId = "myChanellId"
    val notifyId = 1
    val showMessage by remember { mutableStateOf(Hawk.get("showMsg", true)) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                showNotif = true
            }
        }
    )

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    if(showMessage){
        if ( showNotif) {
            Hawk.put("showMsg",false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    chanelId, "My Channel", NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = ""
                }
                val notifyManager: NotificationManager =
                    ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notifyManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(ctx, chanelId)
                .setSmallIcon(R.drawable.icon_info)
                .setContentTitle("آپدیت جدید")
                .setContentText("✅ اضافه شدن اسکرول بی‌نهایت!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(ctx)) {
                notify(notifyId, builder.build())
            }
        }
    }
}
