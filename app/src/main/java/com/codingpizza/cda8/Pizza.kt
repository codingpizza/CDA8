package com.codingpizza.cda8


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pizza {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("description")
    @Expose
    var description: String? = null

}