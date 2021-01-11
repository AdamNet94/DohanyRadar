package hu.bme.aut.android.dohanyradarapp.fragment

import android.R.attr.key
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import hu.bme.aut.android.dohanyradarapp.DetailHelper
import hu.bme.aut.android.dohanyradarapp.R
import hu.bme.aut.android.dohanyradarapp.adapter.StoreAdapter
import hu.bme.aut.android.dohanyradarapp.model.SharedModel
import hu.bme.aut.android.dohanyradarapp.model.Store
import kotlinx.android.synthetic.main.store_list.*


class StoresListFragment: Fragment(), StoreAdapter.StoreItemClickListener {

    public companion object {

    }

    private lateinit var storeAdapter: StoreAdapter
    //private  var stores = mutableListOf<Store>()
    private val model: SharedModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storeAdapter = StoreAdapter(this)

        model.stores?.observe(this, Observer<List<Store>> { stores ->
            storeAdapter.addAll(model.stores.value!!.toList())
        })
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

    override fun onItemClick(clickedStore: Store) {

        DetailHelper.popUpDetails(clickedStore.id.toInt(),this)
        /*val bundle = Bundle()
        bundle.putInt(KEY_STORE_DESCRIPTION, clickedStore.id.toInt())

        val storeDetailsFragment = DetailFragment()
        storeDetailsFragment.arguments = bundle

        //model.getImageFromServer(clickedStore.id.toInt())
        //val backStateName = storeDetailsFragment.javaClass.name
        storeDetailsFragment.setTargetFragment(this,0)
        storeDetailsFragment.show(requireActivity().supportFragmentManager, "TAG")

         */
    }

}