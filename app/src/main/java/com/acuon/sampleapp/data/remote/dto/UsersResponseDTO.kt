package com.acuon.sampleapp.data.remote.dto


import com.acuon.sampleapp.domain.model.UserItem
import com.google.gson.annotations.SerializedName

data class UsersResponseDTOItem(
    @SerializedName("address") val address: Address? = null,
    @SerializedName("company") val company: Company? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("website") val website: String? = null
)

data class Geo(
    @SerializedName("lat") val lat: String? = null, @SerializedName("lng") val lng: String? = null
)

data class Company(
    @SerializedName("bs") val bs: String? = null,
    @SerializedName("catchPhrase") val catchPhrase: String? = null,
    @SerializedName("name") val name: String? = null
)

data class Address(
    @SerializedName("city") val city: String? = null,
    @SerializedName("geo") val geo: Geo? = null,
    @SerializedName("street") val street: String? = null,
    @SerializedName("suite") val suite: String? = null,
    @SerializedName("zipcode") val zipcode: String? = null
)

fun UsersResponseDTOItem.toUserItem() = UserItem(
    id = this.id,
    email = this.email,
    name = this.name,
    phone = this.phone,
    username = this.username,
    website = this.website,
    companyBs = this.company?.bs,
    companyCatchPhrase = this.company?.catchPhrase,
    companyName = this.company?.name,
    city = this.address?.city,
    street = this.address?.street,
    suite = this.address?.suite,
    zipcode = this.address?.zipcode,
    latitude = this.address?.geo?.lat,
    longitude = this.address?.geo?.lng
)