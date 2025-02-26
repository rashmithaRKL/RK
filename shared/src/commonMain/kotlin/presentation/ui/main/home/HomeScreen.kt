package presentation.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rk_shopping.shared.generated.resources.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun HomeScreen(
    onNotificationClick: () -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onProductClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp)
    ) {
        // Location and Notification
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.location),
                    contentDescription = "Location",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Meepadu, Sri Lanka",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFE4E4))
                    .clickable(onClick = onNotificationClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.bell),
                    contentDescription = "Notifications",
                    tint = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text("Search") },
            trailingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.search),
                    contentDescription = "Search",
                    tint = Color.Red
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedIndicatorColor = Color.LightGray,
                focusedIndicatorColor = Color.Red
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Categories
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "See All",
                color = Color.Red,
                modifier = Modifier.clickable(onClick = onCategoryClick)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Icons
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = { onProductClick(category.name) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Special For You Banner
        Text(
            text = "#SpecialForYou",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Banner Image
        AsyncImage(
            model = "https://raw.githubusercontent.com/rashmithaRKL/RK/main/shared/src/commonMain/composeResources/drawable/banner.png",
            contentDescription = "Special Offer",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFE4E4)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(category.icon),
                contentDescription = category.name,
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

data class Category(
    val name: String,
    val icon: DrawableResource
)

private val categories = listOf(
    Category("Computer", Res.drawable.category_computer),
    Category("Electronics", Res.drawable.category_electronics),
    Category("Arts & Crafts", Res.drawable.category_arts),
    Category("Automotive", Res.drawable.category_automotive),
    Category("Baby", Res.drawable.category_baby)
)
