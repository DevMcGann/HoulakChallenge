package com.gsoft.hourakchallenge.presentation.searchScreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(fetchData : (query: String) -> Unit) {
    val textState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Green,
                disabledTextColor = Color.Green,
                placeholderColor = Color.Green,
                containerColor = Color.DarkGray
            ),
            placeholder = { Text(text = "Search by Artist") },
            textStyle = TextStyle(fontSize = 16.sp)
        )
        IconButton(
            onClick = { fetchData(textState.value) },
            enabled = textState.value.isNotEmpty(),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.Green,
                disabledContentColor = Color.Gray
            )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "searchButton",
                //tint = Color.Green,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(fetchData = {})
}