package presentation.productComparison

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String? = null,
    val description: String = "",
    val category: String = "",
    val rating: Float = 0f
) {
    companion object {
        fun fromNavArgs(name: String, price: String, imageUrl: String?): Product {
            return Product(
                name = name.takeIf { it.isNotBlank() } ?: "Unknown Product",
                price = price.takeIf { it.isNotBlank() } ?: "$0.00",
                imageUrl = imageUrl?.takeIf { it.isNotBlank() } 
                    ?: "https://raw.githubusercontent.com/rashmithaRKL/RK/main/shared/src/commonMain/composeResources/drawable/default_image_loader.png"
            )
        }
    }

    val formattedPrice: String
        get() = price.takeIf { it.startsWith("$") } ?: "$$price"
}
