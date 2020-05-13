package edu.boisestate.jsonpractice.Interface

import edu.boisestate.jsonpractice.Model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}