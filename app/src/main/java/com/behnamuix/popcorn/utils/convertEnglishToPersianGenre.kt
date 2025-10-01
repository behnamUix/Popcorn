package com.behnamuix.popcorn.utils

fun convertEngToPerGenre(ids: List<Int?>): String {
    val genreMap = mapOf(
        28 to "اکشن",
        12 to "ماجراجویی",
        16 to "انیمیشن",
        35 to "کمدی",
        80 to "جنایی",
        99 to "مستند",
        18 to "درام",
        10751 to "خانوادگی",
        14 to "فانتزی",
        36 to "تاریخی",
        27 to "ترسناک",
        10402 to "موزیک",
        9648 to "معمایی",
        10749 to "عاشقانه",
        878 to "علمی-تخیلی",
        10770 to "فیلم تلویزیونی",
        53 to "دلهره‌آور",
        10752 to "جنگی",
        37 to "وسترن"
    )

    return ids
        .mapNotNull { genreMap[it] }
        .take(3) // فقط سه تا اول
        .joinToString(" / ")
}

