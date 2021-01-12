package hu.bme.aut.android.dohanyradarapp.model

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.dohanyradarapp.retrofit.TobaccoStoreAPI
import kotlinx.android.synthetic.main.detail_fragment.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SharedModel : ViewModel() {
    var stores = MutableLiveData<List<Store>>()
    var retrofit:Retrofit = Retrofit.Builder()
        .baseUrl("https://dohanyradar.codevisionkft.hu/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val tobaccoAPI: TobaccoStoreAPI = retrofit.create(TobaccoStoreAPI::class.java)

    init{
        loadStores()
    }

    private fun loadStores() {
        val storeCall = tobaccoAPI.getStores()
        storeCall.enqueue(object: Callback<List<Store>> {
            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                Log.d("TAG", "retrofit onFailure, cannot GET Stores list")
            }
            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                stores.value = response.body()
                Log.d(ContentValues.TAG, "Number of store data received: " + (stores.value?.size))
            }
        }
        )
    }
}