package presentation.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import presentation.productComparison.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleProductScreen(
    product: Product,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        // Using Material Design "arrow_back" icon
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
                // Product Name
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Add to Cart Button
                Button(
                    onClick = { /* TODO: Implement add to cart */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Cart")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Add to Wishlist Button
                OutlinedButton(
                    onClick = { /* TODO: Implement add to wishlist */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Wishlist")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Product Description
                Text(
                    text = "Product Description",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Experience premium quality and style with this fantastic product. " +
                            "Made with the finest materials and attention to detail, " +
                            "this item is perfect for any occasion.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
