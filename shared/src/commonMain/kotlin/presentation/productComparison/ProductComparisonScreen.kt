package presentation.productComparison

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
        Text(text = "Compare Products", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                ProductCard(product = currentProduct1)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                ProductCard(product = currentProduct2)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val temp = currentProduct1
            currentProduct1 = currentProduct2
            currentProduct2 = temp
        }) {
            Text(text = "Swap")
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProductImage(product = product)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Price: ${product.price}")
        }
    }
}

@Composable
expect fun ProductImage(product: Product)
