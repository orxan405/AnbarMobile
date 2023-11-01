package com.nexis.anbar

import android.os.Parcel
import android.os.Parcelable

data class dataKlass(
    val userN: String?,
    val userP: String?,
    val userNO: String?,
    val userE: String?,
    val userG: String?,
    val userD: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userN)
        parcel.writeString(userP)
        parcel.writeString(userNO)
        parcel.writeString(userE)
        parcel.writeString(userG)
        parcel.writeString(userD)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<dataKlass> {
        override fun createFromParcel(parcel: Parcel): dataKlass {
            return dataKlass(parcel)
        }

        override fun newArray(size: Int): Array<dataKlass?> {
            return arrayOfNulls(size)
        }

    }


}

