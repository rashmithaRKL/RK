package business.cart

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.productComparison.Product

class CartManager {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(product: Product, quantity: Int = 1) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.product.name == product.name }

        if (existingItem != null) {
            val index = currentItems.indexOf(existingItem)
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + quantity)
        } else {
            currentItems.add(CartItem(product, quantity))
        }
        _cartItems.value = currentItems
    }

    fun removeFromCart(product: Product) {
        val currentItems = _cartItems.value.toMutableList()
        currentItems.removeAll { it.product.name == product.name }
        _cartItems.value = currentItems
    }

    fun updateQuantity(product: Product, quantity: Int) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.product.name == product.name }
        existingItem?.let {
            val index = currentItems.indexOf(it)
            if (quantity <= 0) {
                currentItems.removeAt(index)
            } else {
                currentItems[index] = it.copy(quantity = quantity)
            }
            _cartItems.value = currentItems
        }
    }

    fun getCartTotal(): Double {
        return _cartItems.value.sumOf { 
            val price = it.product.price.removePrefix("$").toDoubleOrNull() ?: 0.0
            price * it.quantity 
        }
    }
}

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)
