package com.behnamuix.popcorn.utils

fun convertJob(engJob: String): String {

    val jobMap = mapOf(
        // مدیریت و تولید
        "Executive Producer" to "تهیه‌کننده اجرایی",
        "Producer" to "تهیه‌کننده",
        "Co-Producer" to "هم‌تهیه‌کننده",
        "Associate Producer" to "تهیه‌کننده دستیار",
        "Line Producer" to "تهیه‌کننده خطی",
        "Production Manager" to "مدیر تولید",
        "Unit Production Manager" to "مدیر تولید واحد",
        "Production Coordinator" to "هماهنگ‌کننده تولید",
        "Production Supervisor" to "ناظر تولید",
        "Production Assistant" to "دستیار تولید",

        // کارگردانی و فیلمبرداری
        "Director" to "کارگردان",
        "Assistant Director" to "دستیار کارگردان",
        "Second Unit Director" to "کارگردان واحد دوم",
        "Director of Photography" to "مدیر فیلمبرداری",
        "Cinematographer" to "فیلمبردار",
        "Camera Operator" to "اپراتور دوربین",
        "First Assistant Camera" to "دستیار اول دوربین",
        "Second Assistant Camera" to "دستیار دوم دوربین",
        "Steadicam Operator" to "اپراتور استدی‌کم",
        "Gaffer" to "سرنورپرداز",
        "Best Boy Electric" to "دستیار برق",
        "Grip" to "گریپ",
        "Key Grip" to "گریپ ارشد",
        "Dolly Grip" to "گریپ دالی",

        // طراحی صحنه و لباس
        "Production Designer" to "طراح صحنه",
        "Art Director" to "مدیر هنری",
        "Set Designer" to "طراح صحنه",
        "Set Decorator" to "تزئین‌کننده صحنه",
        "Props Master" to "مسئول اشیاء صحنه",
        "Costume Designer" to "طراح لباس",
        "Costume Supervisor" to "ناظر لباس",
        "Wardrobe Assistant" to "دستیار لباس",

        // گریم و مو
        "Makeup Artist" to "آرایشگر",
        "Key Makeup Artist" to "آرایشگر ارشد",
        "Hair Stylist" to "سبک‌دهنده مو",
        "Key Hair Stylist" to "سبک‌دهنده مو ارشد",

        // صدا و موسیقی
        "Sound Designer" to "طراح صدا",
        "Sound Editor" to "ویرایشگر صدا",
        "Re-Recording Mixer" to "میکس دوباره صدا",
        "Boom Operator" to "اپراتور بوم",
        "Foley Artist" to "هنرمند افکت صدا",
        "Composer" to "آهنگساز",
        "Music Supervisor" to "ناظر موسیقی",
        "Orchestrator" to "تنظیم‌کننده ارکستر",
        "Dialogue Coach" to "مربی دیالوگ",

        // تدوین
        "Editor" to "تدوینگر",
        "Assistant Editor" to "دستیار تدوینگر",
        "Post Production Supervisor" to "ناظر پس‌تولید",
        "Colorist" to "رنگ‌آمیز",

        // جلوه‌های ویژه و کامپیوتر
        "Visual Effects Supervisor" to "ناظر جلوه‌های ویژه",
        "Visual Effects Coordinator" to "هماهنگ‌کننده جلوه‌های ویژه",
        "VFX Artist" to "هنرمند جلوه‌های ویژه",
        "CG Supervisor" to "ناظر CG",
        "Animation Supervisor" to "ناظر انیمیشن",
        "Animator" to "انیماتور",
        "Character Designer" to "طراح شخصیت",
        "Storyboard Artist" to "هنرمند استوری‌بورد",
        "Layout Artist" to "طراح صحنه انیمیشن",
        "Background Artist" to "هنرمند پس‌زمینه",
        "Texture Artist" to "هنرمند بافت",
        "Rigging Artist" to "هنرمند ریگینگ",
        "Lighting Artist" to "هنرمند نورپردازی",
        "Compositing Artist" to "هنرمند کامپوزیت",
        "Visual Development Artist" to "هنرمند توسعه بصری",
        "Concept Artist" to "هنرمند کانسپت",
        "Environment Designer" to "طراح محیط",
        "Prop Designer" to "طراح اشیاء صحنه",
        "Color Key Artist" to "هنرمند رنگ‌بندی",

        // بدلکاری و حرکت
        "Stunt Coordinator" to "هماهنگ‌کننده بدلکاری",
        "Stunt Performer" to "بازیگر بدلکار",
        "Stunt Double" to "بازیگر بدلکار",
        "Choreographer" to "طراح حرکات",

        // انتخاب بازیگر و فیلمنامه
        "Casting Director" to "کارگردان انتخاب بازیگر",
        "Script Supervisor" to "ناظر فیلمنامه",
        "Screenwriter" to "فیلمنامه‌نویس",
        "Story Consultant" to "مشاور داستان",
        "Story Editor" to "ویرایشگر داستان",
        "Story" to "داستان",
        "Songs" to "موسیقی فیلم",
        "Writer" to "نویسنده",

        // نقش‌های جانبی
        "Set Medic" to "پزشک صحنه",
        "Animal Trainer" to "مربی حیوانات"
    )

    return jobMap[engJob] ?: engJob
}
