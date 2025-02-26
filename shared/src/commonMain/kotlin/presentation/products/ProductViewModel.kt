package presentation.products

import business.data.ProductData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.productComparison.Product

class ProductViewModel {
    private val _tShirts = MutableStateFlow(ProductData.mensTShirts)
    val tShirts: StateFlow<List<Product>> = _tShirts.asStateFlow()

    private val _trousers = MutableStateFlow(ProductData.mensTrousers)
    val trousers: StateFlow<List<Product>> = _trousers.asStateFlow()

    private val _shoes = MutableStateFlow(ProductData.mensShoes)
    val shoes: StateFlow<List<Product>> = _shoes.asStateFlow()

    fun getTShirts(): List<Product> = ProductData.mensTShirts
    fun getTrousers(): List<Product> = ProductData.mensTrousers
    fun getShoes(): List<Product> = ProductData.mensShoes
}
