package com.behnamuix.popcorn.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.behnamuix.popcorn.utils.date.JalaliDate

@RequiresApi(Build.VERSION_CODES.O)
fun convertMiladiToShamsi(miladi:String):String{
    val jd= JalaliDate(miladi)
    return jd.asString()

}