package hu.bme.aut.android.dohanyradarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import hu.bme.aut.android.dohanyradarapp.adapter.TobaccoPageAdapter
import hu.bme.aut.android.dohanyradarapp.fragment.MapFragment
import hu.bme.aut.android.dohanyradarapp.fragment.StoresListFragment
import hu.bme.aut.android.dohanyradarapp.model.SharedModel
import hu.bme.aut.android.dohanyradarapp.model.Store
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Thread.sleep(2000)
            setTheme(R.style.AppTheme)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: SharedModel by viewModels()


        val pageAdapter = TobaccoPageAdapter(supportFragmentManager)
        pageAdapter.addFrag(StoresListFragment(),"StoreList")
        pageAdapter.addFrag(MapFragment(),"StoreMap")
        vpTobacco.adapter = pageAdapter
    }


}