package com.example.pertemuan_12_2.network

import com.example.pertemuan_12_2.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data.php") /// ini akan menjadi parameter yang ditambahkan setelah slash BASE URL pada ApiClient.kt
    fun getDataData(): Call<DataModel>
}