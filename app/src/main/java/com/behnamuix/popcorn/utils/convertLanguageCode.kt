package com.behnamuix.popcorn.utils

fun convertLanguageCode(code:String): String {

    val languageMap = mapOf(
        "en" to "انگلیسی",
        "fa" to "فارسی",
        "ar" to "عربی",
        "fr" to "فرانسوی",
        "de" to "آلمانی",
        "es" to "اسپانیایی",
        "it" to "ایتالیایی",
        "tl" to "فیلیپینی / تگالوگ",
        "pt" to "پرتغالی",
        "ru" to "روسی",
        "ja" to "ژاپنی",
        "zh" to "چینی",
        "hi" to "هندی",
        "ur" to "اردو",
        "tr" to "ترکی استانبولی",
        "ko" to "کره‌ای",
        "th" to "تایلندی",
        "ms" to "مالایی",
        "id" to "اندونزیایی",
        "bn" to "بنگالی",
        "ta" to "تامیلی",
        "te" to "تلوگو",
        "ml" to "مالایالمی",
        "uk" to "اوکراینی",
        "pl" to "لهستانی",
        "sv" to "سوئدی",
        "da" to "دانمارکی",
        "no" to "نروژی",
        "fi" to "فنلاندی",
        "el" to "یونانی",
        "he" to "عبری",
        "cs" to "چکی",
        "ro" to "رومانیایی",
        "hu" to "مجاری",
        "sr" to "صربی",
        "bg" to "بلغاری",
        "nl" to "هلندی"
    )



    return languageMap[code] ?: code
}

