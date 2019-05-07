package pe.edu.upc.catchup

import com.androidnetworking.AndroidNetworking
import com.jacksonandroidnetworking.JacksonParserFactory
import com.orm.SugarApp
import pe.edu.upc.catchup.network.NewsApi

class CatchUpApp : SugarApp() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.setParserFactory(JacksonParserFactory())
        NewsApi.apiKey = applicationContext.getString(R.string.new_api_key)
        NewsApi.refreshSourcesCaching()
    }
}