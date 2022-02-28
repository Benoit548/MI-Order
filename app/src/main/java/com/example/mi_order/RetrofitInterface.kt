package com.example.mi_order

import retrofit2.Call
import retrofit2.http.GET
import com.example.mi_order.models.PostModel

interface RetrofitInterface {

    @get:GET("users")
    val posts : Call<List<PostModel?>?>?

    companion object {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
}
}