package edu.boisestate.jsonpractice.Common

import edu.boisestate.jsonpractice.Interface.RetrofitService
import edu.boisestate.jsonpractice.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"

    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}