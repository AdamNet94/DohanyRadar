package hu.bme.aut.android.dohanyradarapp.retrofit

import androidx.lifecycle.LiveData
import hu.bme.aut.android.dohanyradarapp.model.Store
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TobaccoStoreAPI {
    @GET("tobbacoshop")
    fun getStores(): Call<List<Store>>

    @GET("tobbacoshop/{id}/image")
    fun getImage(@Path("id") storeId: Int): Call<ResponseBody>
}