package com.example.mybooks

import android.os.Parcel
import android.os.Parcelable

data class Livro(
    var id: Int?,
    val titulo: String?,
    val autor: String?,
    val tipo: String?,
    val paginas: Int?,
    val lidas: Int?,
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(titulo)
        parcel.writeString(autor)
        parcel.writeString(tipo)
        parcel.writeValue(paginas)
        parcel.writeValue(lidas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Livro> {
        override fun createFromParcel(parcel: Parcel): Livro {
            return Livro(parcel)
        }

        override fun newArray(size: Int): Array<Livro?> {
            return arrayOfNulls(size)
        }
    }
}