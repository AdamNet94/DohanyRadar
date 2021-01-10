package hu.bme.aut.android.dohanyradarapp.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.store_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvStores.adapter = storeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        var menuItem = menu.findItem(R.id.search)
        var searchView = menuItem.actionView as SearchView
        var queryTextListener = QueryTextListner()
        searchView.setOnQueryTextListener(queryTextListener)
    }

    inner class QueryTextListner : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return false;
        }
        override fun onQueryTextChange(p0: String?): Boolean {
            storeAdapter.storefilter.filter(p0)
            return false;
        }
    }
}