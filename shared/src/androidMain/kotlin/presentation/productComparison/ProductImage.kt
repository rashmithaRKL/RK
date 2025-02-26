package presentation.productComparison

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
actual fun ProductImage(product: Product) {
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
        error = painterResource(com.razzaghi.rkshopping.android.R.drawable.default_image_loader),
        placeholder = painterResource(com.razzaghi.rkshopping.android.R.drawable.default_image_loader)
    )
}
