package presentation.productComparison

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String? = null // Optional image URL with default value of null
)
