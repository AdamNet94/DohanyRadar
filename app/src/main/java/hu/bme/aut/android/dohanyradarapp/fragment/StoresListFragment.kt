package hu.bme.aut.android.dohanyradarapp.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.android.dohanyradarapp.R
import hu.bme.aut.android.dohanyradarapp.adapter.StoreAdapter
import hu.bme.aut.android.dohanyradarapp.model.Store
import hu.bme.aut.android.dohanyradarapp.retrofit.TobaccoStoreAPI
import kotlinx.android.synthetic.main.store_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoresListFragment: Fragment(){

    private lateinit var storeAdapter: StoreAdapter
    private  var stores = mutableListOf<Store>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storeAdapter = StoreAdapter()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dohanyradar.codevisionkft.hu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tobaccoAPI = retrofit.create(TobaccoStoreAPI::class.java)
        val storeCall = tobaccoAPI.getStores()
        storeCall.enqueue(object: Callback<List<Store>> {
            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                stores = response.body() as MutableList<Store>
                storeAdapter.addAll(stores)
                Log.d(TAG, "Number of movies received: " + stores.size)
            }
        }
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.store_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvStores.adapter = storeAdapter
    }
}