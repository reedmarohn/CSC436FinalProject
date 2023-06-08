package com.example.lab3nav.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab3nav.R
import com.example.lab3nav.data.InventoryUiState

@Composable
fun ListScreen(
    prodList : List<InventoryUiState>,
    onDoneButtonClicked : () -> Unit = {},
    modifier : Modifier = Modifier
) {
        Column(modifier = Modifier.fillMaxSize()){
            LazyColumn(
                Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(prodList) { data ->
                    createCard(data = data)
                }
            }
            Button(onClick = onDoneButtonClicked, enabled = true, shape = CircleShape) {
                Text(text = "+")
            }
        }


}
@Composable
fun createCard(data: InventoryUiState) {
        Card(
            shape = RoundedCornerShape(size = 12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(all = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.fashion),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(width = 8.dp)) // gap between image and text

                Text(buildAnnotatedString {
                    //use this to add multiple lines of data in one Text call
                    withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                        // NAME
                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                            append("Product Name: ")
                        }
                        append(text = data.productName)

                        //CATEGORY
                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                            append("\nProduct Category: ")
                        }
                        append(text = data.productCategory)

                        //EXPIRATION
                        withStyle(style = SpanStyle( fontWeight = FontWeight.SemiBold)) {
                            append("\nExpiration Date: ")
                        }
                        append(text = data.expirationDate)
                    }
                })// end of text
            } // end of row
        }// end of card
}

