package hu.bme.aut.android.dohanyradarapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import hu.bme.aut.android.dohanyradarapp.fragment.DetailFragment

class DetailHelper {
    companion object {

        const val KEY_STORE_DESCRIPTION = "STOREID"

        fun popUpDetails(storeId : Int, fragment: Fragment) {

            val bundle = Bundle()
            bundle.putInt(KEY_STORE_DESCRIPTION, storeId)
            val storeDetailsFragment = DetailFragment()
            storeDetailsFragment.arguments = bundle
            storeDetailsFragment.setTargetFragment(fragment,0)
            storeDetailsFragment.show(fragment.requireActivity().supportFragmentManager, "TAG")
        }
    }

}