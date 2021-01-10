package hu.bme.aut.android.dohanyradarapp.retrofit

import hu.bme.aut.android.dohanyradarapp.model.Store
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TobaccoStoreAPI {
    @GET("tobbacoshop")
    fun getStores(): Call<List<Store>>

  /*  @GET("tobbacoshop/{id}/image")
    fun getImage(@Path("id") storeId: Int, @Query("base") base: String): Call<bitmap>*/
}