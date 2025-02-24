package de.syntax_institut.fakeStore

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage


@Composable
fun ImageView(
    modifier: Modifier = Modifier
) {


//    var search by remember { mutableStateOf("") }
//    var isSearchVisible by remember { mutableStateOf(false) }
//
//    val filteredLinksImage = nasaLink.filter { it.rel == "preview" /*&& it.href.isNotEmpty()*/ }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//    ) {
//        if (filteredLinksImage.isNotEmpty()) {
//            LazyVerticalGrid(
//                modifier = Modifier
//                    .fillMaxSize(),
//                columns = GridCells.Fixed(3),
//                horizontalArrangement = Arrangement.Center,
//                verticalArrangement = Arrangement.Center
//            ) {
//
//                itemsIndexed(filteredLinksImage) { index, nasaLink ->
//                    println(nasaLink)
//                    println(nasaData)
//                    val nasaDataItem = nasaData[index]
//
//                    ElevatedCard(
//                        modifier = Modifier
//                            .padding(4.dp)
//                            .clickable { onNavigateToDetailViewImage(nasaLink, nasaDataItem) },
//
//                        elevation = CardDefaults.elevatedCardElevation(4.dp),
//
//                        ) {
//
//                        Box(
//                            modifier = Modifier
//                                .height(100.dp)
//                                .fillMaxWidth(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            SubcomposeAsyncImage(
//                                model = nasaLink.href,
//                                contentDescription = "Nasa Image Detail",
//                                modifier = Modifier
//                                    .height(100.dp),
//                                contentScale = ContentScale.Crop,
//                                onLoading = {
//                                    Log.d("AsyncImage", "Image loading...")
//                                },
//                                onSuccess = { state ->
//                                    Log.d("AsyncImage", "Image loaded successfully")
//                                },
//                                loading = {
//                                    Box(
//                                        modifier = Modifier.fillMaxSize(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        CircularProgressIndicator(
//                                            modifier = Modifier.scale(1f))
//                                    }
//                                },
//                                )
//                        }
//                    }
//                }
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text("Error loading images!")
//                Text("Check your Internet Connection..")
//                Spacer(Modifier.padding(vertical = 6.dp))
//                Icon(
//                    painter = painterResource(R.drawable.baseline_error_outline_24),
//                    null
//                )
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Bottom,
//        horizontalAlignment = Alignment.End
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.End
//        ) {
//
//            if (isSearchVisible) {
//                TextField(
//                    value = search,
//                    onValueChange = {
//                        search = it.lowercase()
//                        imageViewModel.loadNasaImage(it)
//                    },
//
//                    placeholder = { Text("Search..") },
//                    modifier = Modifier
//                        .padding(horizontal = 4.dp),
//                    shape = RoundedCornerShape(16.dp),
//                )
//            }
//            FloatingActionButton(
//                modifier = Modifier
//                    .padding(16.dp),
//                onClick = { isSearchVisible = !isSearchVisible },
//                containerColor = Color(0xFF003B5C)
//            ) {
//                Icon(
//                    Icons.Default.Search,
//                    contentDescription = "Search Icon",
//
//                )
//            }
//        }
//    }
}





