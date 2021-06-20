package com.dg.eqs.util.serialization

import android.os.Parcel
import android.os.Parcelable


fun serialize(parcelable: Parcelable): ByteArray = Parcel.obtain().run {
    writeParcelable(parcelable, 0)
    marshall()
}

inline fun <reified T : Parcelable> deserialize(bytes: ByteArray) = Parcel.obtain().run {
    unmarshall(bytes, 0, bytes.size)
    setDataPosition(0)
    readParcelable<T>(T::class.java.classLoader)
}