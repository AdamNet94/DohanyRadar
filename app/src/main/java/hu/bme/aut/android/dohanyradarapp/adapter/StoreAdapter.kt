package hu.bme.aut.android.dohanyradarapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.dohanyradarapp.R
import hu.bme.aut.android.dohanyradarapp.model.Store
import kotlinx.android.synthetic.main.store_rowitem.view.*

class StoreAdapter: RecyclerView.Adapter<StoreAdapter.ViewHolder>(){

    private val storeList = mutableListOf<Store>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.store_rowitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = storeList[position]
        holder.tvName.text = store.name
        holder.tvAdress.text = store.address
        holder.tvisOpen.text = if(store.isOpen) "Nyitva" else "Zárva"

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = itemView.StoreName
        val tvAdress: TextView = itemView.StoreAddress
        val tvisOpen: TextView  = itemView.isOpen
    }

    override fun getItemCount() =  storeList.size

    fun addAll(stores: List<Store>) {
        storeList.clear()
        storeList.addAll(stores)
        notifyDataSetChanged()
    }
}