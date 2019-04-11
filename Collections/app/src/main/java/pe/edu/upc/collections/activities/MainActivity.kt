package pe.edu.upc.collections.activities

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.collections.R
import pe.edu.upc.collections.fragments.HomeFragment
import pe.edu.upc.collections.fragments.ItemsFragment
import pe.edu.upc.collections.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigateTo(navigation.menu.findItem(R.id.navigation_home))
    }

    private fun getFragmentFor(item: MenuItem) : Fragment {
        when (item.itemId) {
            R.id.navigation_home -> {
                return HomeFragment()
            }
            R.id.navigation_items -> {
                return ItemsFragment()
            }
            R.id.navigation_settings -> {
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
