package presentation.productComparison

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductComparisonScreen(product1: Product, product2: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Compare Products", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProductCard(product = product1)
            Spacer(modifier = Modifier.width(16.dp))
            ProductCard(product = product2)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Handle Swap Action */ }) {
            Text(text = "Swap")
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.weight(1f),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            // Load product image here
            Text(text = "Price: ${product.price}")
            // Add more product details as needed
        }
    }
}

data class Product(val name: String, val price: String)
