package com.behnamuix.popcorn.utils

fun convertToHourFormat(runtime: Int): String {
    var h = runtime / 60
    var m = runtime % 60
    return String.format("%02d:%02d", h, m)
}