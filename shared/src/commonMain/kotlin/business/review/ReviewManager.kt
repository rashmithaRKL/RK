package business.review

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class ReviewManager {
    private val _reviews = MutableStateFlow<Map<String, List<Review>>>(emptyMap())
    val reviews: StateFlow<Map<String, List<Review>>> = _reviews.asStateFlow()

    fun addReview(productName: String, review: Review) {
        val currentReviews = _reviews.value.toMutableMap()
        val productReviews = currentReviews.getOrDefault(productName, emptyList()).toMutableList()
        productReviews.add(review)
        currentReviews[productName] = productReviews
        _reviews.value = currentReviews
    }

    fun getReviewsForProduct(productName: String): List<Review> {
        return _reviews.value[productName] ?: emptyList()
    }

    fun getAverageRating(productName: String): Float {
        val productReviews = _reviews.value[productName] ?: return 0f
        if (productReviews.isEmpty()) return 0f
        return productReviews.map { it.rating }.average().toFloat()
    }
}

data class Review(
    val userName: String,
    val rating: Int,
    val comment: String,
    val timestamp: Instant = Clock.System.now()
) {
    init {
        require(rating in 1..5) { "Rating must be between 1 and 5" }
    }
}
