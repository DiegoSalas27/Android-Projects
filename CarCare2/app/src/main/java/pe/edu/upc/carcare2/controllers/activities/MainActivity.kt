package pe.edu.upc.carcare2.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.carcare2.R
import pe.edu.upc.carcare2.controllers.fragments.*

class MainActivity : AppCompatActivity() {

    private val onTabSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        navigateTo(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(onTabSelected)
        navigateTo(navigation.menu.findItem(R.id.navigation_home))
    }

    private fun getFragmentFor(item: MenuItem) : Fragment {
        when (item.itemId) {
            R.id.navigation_home -> {
                return HomeFragment()
            }
            R.id.navigation_fuelups -> {
                return FuelupsFragment()
            }
            R.id.navigation_location -> {
                return LocationFragment()
            }
            R.id.navigation_settins -> {
                return servicesFragment()
            }
            R.id.navigation_settins -> {
                return SettingsFragment()
            }
        }
        return HomeFragment()
    }

    private fun navigateTo(item: MenuItem) : Boolean {
        item.isChecked = true
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, getFragmentFor(item))
            .commit() > 0
    }
}
