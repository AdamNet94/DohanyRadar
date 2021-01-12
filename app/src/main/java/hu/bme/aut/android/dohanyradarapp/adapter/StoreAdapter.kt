package hu.bme.aut.android.dohanyradarapp.adapter

import android.graphics.Color
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.dohanyradarapp.R
import hu.bme.aut.android.dohanyradarapp.model.Store
import kotlinx.android.synthetic.main.store_rowitem.view.*

class StoreAdapter(private var itemClickListener: StoreItemClickListener?): RecyclerView.Adapter<StoreAdapter.ViewHolder>(), Filterable{

    private val storeList = mutableListOf<Store>()
    private var storeListFull = mutableListOf<Store>()

    var storefilter = StoreFilter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.store_rowitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = storeList[position]
        holder.store = store
        holder.tvName.text = store.name
        holder.tvAdress.text = store.address

        if(store.isOpen){
            holder.tvisOpen.text = "Nyitva"
            holder.tvisOpen.setTextColor(Color.GREEN)
        }
        else {
            holder.tvisOpen.text = "Zárva"
            holder.tvisOpen.setTextColor(Color.RED)
        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = itemView.StoreName
        val tvAdress: TextView = itemView.StoreAddress
        val tvisOpen: TextView  = itemView.isOpen
        var store :Store? = null



        init {
            itemView.setOnClickListener {
                store?.let { store -> itemClickListener?.onItemClick(store) }
            }
        }
    }

    override fun getItemCount() =  storeList.size

    fun addAll(stores: List<Store>) {
        storeList.clear()
        storeList.addAll(stores)
        storeListFull = mutableListOf<Store>().apply{ addAll(storeList)}
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return storefilter
        };

    inner class StoreFilter(): Filter() {

        override fun performFiltering(p0: CharSequence?): FilterResults {
            var filteredList = mutableListOf<Store>()
            if(p0 == null || p0.isEmpty())
                filteredList.addAll(storeListFull)
            else {
                for(store in storeListFull)
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
            storeList.clear()
            Log.d("TAG","store szie after clear:"+ storeList.size.toString())
            storeList.addAll(p1!!.values as Collection<Store>)
            Log.d("TAG","store szie after filter:"+ storeList.size.toString())
            notifyDataSetChanged()
        }

    }

    interface StoreItemClickListener{
        fun onItemClick(clickedStore: Store)
    }
}