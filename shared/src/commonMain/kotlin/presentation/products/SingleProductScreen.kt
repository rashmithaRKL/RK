package presentation.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import presentation.productComparison.Product
import business.cart.CartManager
import business.review.Review
import business.review.ReviewManager
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleProductScreen(
    product: Product,
    cartManager: CartManager,
    reviewManager: ReviewManager,
    onBackClick: () -> Unit
) {
    var quantity by remember { mutableStateOf(1) }
    var showReviewDialog by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(5) }
    var reviewComment by remember { mutableStateOf("") }
    val reviews by remember(product.name) { 
        derivedStateOf { reviewManager.getReviewsForProduct(product.name) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Product Image
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            // Product Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.price,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Quantity Selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Quantity: ")
                    IconButton(
                        onClick = { if (quantity > 1) quantity-- },
                        enabled = quantity > 1
                    ) {
                        Text("-")
                    }
                    Text(quantity.toString())
                    IconButton(
                        onClick = { quantity++ }
                    ) {
                        Text("+")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Add to Cart Button
                Button(
                    onClick = { 
                        cartManager.addToCart(product, quantity)
                        quantity = 1 // Reset quantity after adding to cart
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Cart ($quantity)")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Reviews Section
                Text(
                    text = "Reviews",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Add Review Button
                Button(
                    onClick = { showReviewDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Write a Review")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Reviews List
                reviews.forEach { review ->
                    ReviewItem(review)
                    Divider()
                }
            }
        }

        // Review Dialog
        if (showReviewDialog) {
            AlertDialog(
                onDismissRequest = { showReviewDialog = false },
                title = { Text("Write a Review") },
                text = {
                    Column {
                        // Rating Selector
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (i in 1..5) {
                                IconButton(
                                    onClick = { rating = i }
                                ) {
                                    Text(
                                        text = "★",
                                        color = if (i <= rating) MaterialTheme.colorScheme.primary 
                                               else MaterialTheme.colorScheme.outline
                                    )
                                }
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Review Text Field
                        TextField(
                            value = reviewComment,
                            onValueChange = { reviewComment = it },
                            label = { Text("Your Review") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (reviewComment.isNotBlank()) {
                                reviewManager.addReview(
                                    product.name,
                                    Review(
                                        userName = "User", // In a real app, get from user profile
                                        rating = rating,
                                        comment = reviewComment
                                    )
                                )
                                showReviewDialog = false
                                reviewComment = ""
                                rating = 5
                            }
                        }
                    ) {
                        Text("Submit")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showReviewDialog = false }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
private fun ReviewItem(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = review.userName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "★".repeat(review.rating),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = review.comment,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
