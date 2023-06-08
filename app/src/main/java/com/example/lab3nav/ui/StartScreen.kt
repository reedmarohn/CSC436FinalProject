package com.example.lab3nav.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab3nav.R
import kotlin.math.min


@Composable
fun StartScreen(
    viewModel: InventoryViewModel,
    onNewItemClicked: () -> Unit,
    onViewAllClicked: () -> Unit,
    modifier: Modifier = Modifier
){
 Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            AsyncImage(
                model = stringResource(R.string.Image),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
        SummarizedView(viewModel = viewModel)
        Row(modifier = Modifier.weight(1f, false)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                Button(
                    onClick = onNewItemClicked
                ) {
                    Text(stringResource(R.string.New_Item))
                }
                Button(
                    onClick = onViewAllClicked
                ) {
                    Text(stringResource(R.string.View_All_Items))
                }
            }
        }
    }
}

@Composable
fun SummarizedView(viewModel: InventoryViewModel){
    if(viewModel.productList.isNotEmpty()) {
        Column(){
            viewModel.productList.subList(0, min(4, viewModel.productList.size)).forEach{ product ->
                CreateCard(product)
            }
        }
    }
}

