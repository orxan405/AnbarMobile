package com.nexis.anbar.data.model

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class tblQeydiyyat(
    val oid: String? = "",
    var istifade: String? = "",
    var parolu: String? = "",
    var mail: String? = "",
    var nomre: String? = "",
    var cins: String? = "",
    var dogumT: String? = "",
    var ad: String? = "",
    var soyad: String? = "",
    var ata_ad: String? = ""
)
