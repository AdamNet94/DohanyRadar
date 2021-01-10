package hu.bme.aut.android.dohanyradarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.dohanyradarapp.adapter.TobaccoPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Thread.sleep(2000)
            setTheme(R.style.AppTheme)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpTobacco.adapter = TobaccoPageAdapter(supportFragmentManager)
    }
}