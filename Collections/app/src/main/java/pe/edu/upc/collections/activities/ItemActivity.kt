package pe.edu.upc.collections.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import pe.edu.upc.collections.R

import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.content_items.*

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.extras?.apply {//si inicia invocado desde un item haz esto
            firstNameTextView.text = getString("first_name")
            lastNameTextView.text = getString("last_name")
        }
    }

}
