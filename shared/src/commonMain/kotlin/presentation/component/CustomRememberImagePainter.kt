package presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rk_shopping.shared.generated.resources.Res
import rk_shopping.shared.generated.resources.default_image_loader

@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberCustomImagePainter(
    model: Any?,
    imageLoader: ImageLoader,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model, imageLoader,
    error = painterResource(Res.drawable.default_image_loader),
    placeholder = painterResource(Res.drawable.default_image_loader),
    contentScale = contentScale
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberCustomImagePainter(
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model,
    error = painterResource(Res.drawable.default_image_loader),
    placeholder = painterResource(Res.drawable.default_image_loader),
    contentScale = contentScale,
)