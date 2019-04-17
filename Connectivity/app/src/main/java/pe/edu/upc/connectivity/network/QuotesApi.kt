package pe.edu.upc.connectivity.network

class QuotesApi {
    companion object {
        val BASE_URL = "https://quotes.rest"
        fun quoteOfDayUrl(): String {
            return "${BASE_URL}/qod.json"
        }
    }
}