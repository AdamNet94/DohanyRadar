package hu.bme.aut.android.dohanyradarapp.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import hu.bme.aut.android.dohanyradarapp.R
import hu.bme.aut.android.dohanyradarapp.model.SharedModel
import hu.bme.aut.android.dohanyradarapp.model.Store
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment: DialogFragment() {

    lateinit var  selectedStore: Store
    private val model: SharedModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreen)
        var storeID = -1
        arguments?.let { args ->
            storeID = args.getInt("STOREID")
        }
        // stores ID from the REST api starts with 1 and Arryas/Lists are indexed from 0, hence the -1
        selectedStore = model.stores.value!![storeID-1]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAddress.text = selectedStore.address
        tvStoreName.text = selectedStore.name
        tvOpen.text = if(selectedStore.isOpen) getString(R.string.nyitva) else getString(R.string.zarva)
        Glide.with(this).load("https://dohanyradar.codevisionkft.hu/tobbacoshop/${selectedStore.id.toInt()}/image").into(ivImage)
    }

}