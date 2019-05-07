package pe.edu.upc.connectivity.network

import pe.edu.upc.connectivity.models.QuoteItem

data class QuoteOfDayContentSection(
    val quotes: List<QuoteItem>,
    val copyright: String

) {
    constructor() : this(ArrayList<QuoteItem>(), "")
}
