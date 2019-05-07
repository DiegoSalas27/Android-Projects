package pe.edu.upc.connectivity.models

data class QuoteItem(
    val quote: String,
    val length: String,
    val author: String,
    val tags: List<String>,
    val category: String,
    val date: String,
    val title: String,
    val background: String,
    val id: String,
    val permalink: String
) {
    constructor() : this(
        "",
        "",
        "",
        ArrayList<String>(),
        "",
        "",
        "",
        "",
        "",
        "")
}
