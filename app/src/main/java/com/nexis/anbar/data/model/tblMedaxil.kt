package com.nexis.anbar.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class tblMedaxil(
    var malinAdi: String? = "",
    var sayi: String? = "",
    var alisQiymeti: String? = "",
    var satisQiymeti: String? = ""
)
