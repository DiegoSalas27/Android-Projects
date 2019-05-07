package pe.edu.upc.connectivity.network

class QuotesApi {
    companion object {
        val BASE_URL = "http://quotes.rest"
        fun quoteOfDayUrl(): String {
            return "${BASE_URL}/qod.json"
        }
    }
}