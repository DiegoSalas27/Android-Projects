package pe.edu.upc.connectivity.controllers

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pe.edu.upc.connectivity.R
import pe.edu.upc.connectivity.network.QuoteOfDayResponse
import pe.edu.upc.connectivity.network.QuotesApi

class MainActivity : AppCompatActivity() {
    val TAG = "Quotes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        requestQuoteOfDay()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun requestQuoteOfDay() {
        AndroidNetworking.get(QuotesApi.quoteOfDayUrl())
            .addQueryParameter("category", "students") //http://quotes.rest/qod.json/?category=students
            .setPriority(Priority.LOW)
            .setTag(TAG)
            .build()
            .getAsObject(QuoteOfDayResponse::class.java,
                object: ParsedRequestListener<QuoteOfDayResponse> {
                    override fun onResponse(response: QuoteOfDayResponse?) {
                        response?.apply {
                            success.apply {
                                Log.i(TAG, "Total results: ${total}")
                            }
                            contents.apply {
                                quotes.forEach {
                                    Log.i(TAG, "Quote: ${it.quote}")
                                    Log.i(TAG, "Tags: ${it.tags.joinToString()}")
                                    Log.i(TAG, "Background: ${it.background}")
                                    backgroundImageView.setDefaultImageResId(R.mipmap.ic_launcher)
                                    backgroundImageView.setErrorImageResId(R.mipmap.ic_launcher)
                                    backgroundImageView.setImageUrl(it.background)
                                    quoteTextView.text = it.quote
                                }
                            }
                        }
                    }

                    override fun onError(anError: ANError?) {
                        anError?.apply { Log.d(TAG, "${this.errorBody}")}
                    }

                })

    }
}
