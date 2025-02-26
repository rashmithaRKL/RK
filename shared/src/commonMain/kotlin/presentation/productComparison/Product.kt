package presentation.productComparison

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String? = null
) {
    companion object {
        fun fromNavArgs(name: String, price: String, imageUrl: String?): Product {
            return Product(
                name = name.takeIf { it.isNotBlank() } ?: "Unknown Product",
                price = price.takeIf { it.isNotBlank() } ?: "$0.00",
                imageUrl = imageUrl?.takeIf { it.isNotBlank() }
            )
        }
    }
}
