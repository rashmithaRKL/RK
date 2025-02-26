package presentation.productComparison

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

@Composable
fun ProductComparisonScreen(product1: Product, product2: Product) {
    var currentProduct1 by remember { mutableStateOf(product1) }
    var currentProduct2 by remember { mutableStateOf(product2) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Compare Products", style = MaterialTheme.typography.headlineLarge)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProductCard(product = currentProduct1)
            Spacer(modifier = Modifier.width(16.dp))
            ProductCard(product = currentProduct2)
        }

        Button(onClick = {
            // Swap products
            val temp = currentProduct1
            currentProduct1 = currentProduct2
            currentProduct2 = temp
        }) {
            Text("Swap")
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Product Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Product image for ${product.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Fit,
                error = ColorBox(color = MaterialTheme.colorScheme.errorContainer),
                placeholder = ColorBox(color = MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, style = MaterialTheme.typography.titleMedium)
            Text("Price: ${product.price}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun ColorBox(color: androidx.compose.ui.graphics.Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    )
}
