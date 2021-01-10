package hu.bme.aut.android.dohanyradarapp.model

data class Store(val id: Number,
                 val name: String,
                 val address: String,
                 val longitude: Number,
                 val latitude: Number,
                 val isOpen: Boolean)