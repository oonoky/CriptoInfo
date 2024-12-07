package com.example.cryptoinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.cryptoinfo.model.Crypto
import com.example.cryptoinfo.viewmodel.CryptoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: CryptoViewModel = viewModel()
            CryptoApp(viewModel)
        }
    }
}

@Composable
fun CryptoApp(viewModel: CryptoViewModel) {
    val cryptos = viewModel.cryptoList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CryptoInfo") }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(cryptos.value) { crypto ->
                CryptoItem(crypto)
            }
        }
    }
}

@Composable
fun CryptoItem(crypto: Crypto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(crypto.image),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(crypto.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(crypto.symbol.uppercase(), color = androidx.compose.ui.graphics.Color.Gray)
            Text("$${crypto.current_price}", fontWeight = FontWeight.Medium)
            Text("Updated: ${crypto.last_updated}", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Gray)
        }
    }
}
