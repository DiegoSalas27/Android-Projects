package pe.edu.upc.connectivity.network

data class QuoteOfDayResponse(
    val success: QuoteOfDaySuccessSection,
    val contents: QuoteOfDayContentSection
) {
    constructor() : this(QuoteOfDaySuccessSection(), QuoteOfDayContentSection())
}