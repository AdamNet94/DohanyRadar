package hu.bme.aut.android.dohanyradarapp

import android.widget.Filter
import hu.bme.aut.android.dohanyradarapp.model.Store

class StoreFilter(val fullList: List<Store>): Filter() {
    var filteredList = mutableListOf<Store>()

    override fun performFiltering(p0: CharSequence?): FilterResults {
            if(p0 == null || p0.isEmpty())
                filteredList.addAll(fullList)
            else {
                for(store in fullList)
                {
                    val search = p0.toString().toLowerCase().trim()
                    if(store.name.toLowerCase().contains(search))
                        filteredList.add(store)
                }
            }
       var results = FilterResults()
        results.values = filteredList
        return results
    }

    override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
        
    }

}