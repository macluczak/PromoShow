package com.example.api.shop.model

import android.os.Parcel
import android.os.Parcelable
import com.example.api.product.model.Product
import kotlinx.serialization.Serializable
import java.util.Date
import java.util.UUID

@Serializable
data class Shop(
    val id: UUID?,
    val name: String,
    val description: String,
    val image: String,
    val locationCode: String,
    val products: List<Product>,
    val createdAt: Date?,
    val updatedAt: Date?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as UUID?,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        mutableListOf<Product>().apply {
            parcel.readList(this, Product::class.java.classLoader)
        },
        parcel.readSerializable() as Date?,
        parcel.readSerializable() as Date?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(locationCode)
        parcel.writeList(products)
        parcel.writeSerializable(createdAt)
        parcel.writeSerializable(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shop> {
        override fun createFromParcel(parcel: Parcel): Shop {
            return Shop(parcel)
        }

        override fun newArray(size: Int): Array<Shop?> {
            return arrayOfNulls(size)
        }
    }
}
