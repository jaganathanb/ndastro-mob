package dev.dapps.ndastro.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import dev.dapps.ndastro.BuildConfig

@Composable
fun SvgImageFromApi(
    svgUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val context = LocalContext.current

    // Set up Coil image loader with SVG decoder
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                add(OkHttpNetworkFetcherFactory())
                add(SvgDecoder.Factory(useViewBoundsAsIntrinsicSize = false))
            }
            .build()
    }

    var isLoading by remember { mutableStateOf(true) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(BuildConfig.BASE_URL + svgUrl)
                .crossfade(true)
                .networkCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCacheKey(svgUrl)
                .memoryCacheKey(svgUrl)
                .listener(
                    onSuccess = { _, _ -> isLoading = false },
                    onError = { _, e -> isLoading = false
                        e.throwable.printStackTrace()
                    }
                )
                .build(),
            imageLoader = imageLoader,
            contentDescription = contentDescription,
            contentScale = ContentScale.FillBounds,
            filterQuality = FilterQuality.None
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(36.dp))
        }
    }
}