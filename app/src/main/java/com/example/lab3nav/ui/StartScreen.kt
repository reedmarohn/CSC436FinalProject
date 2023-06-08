package com.example.lab3nav.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3nav.R
import java.text.SimpleDateFormat
import kotlin.math.min


@Composable
fun StartScreen(
    viewModel: InventoryViewModel,
    onNextButtonClicked: () -> Unit,
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
           // Image(
           //     painter = painterResource(R.drawable.cupcake),
           //     contentDescription = null,
           //     modifier = Modifier.width(300.dp)
           // )
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
                    onClick = onNextButtonClicked
                ) {
                    Text(text = stringResource(R.string.list_all))
                }
            }
        }
    }
}

@Composable
fun SummarizedView(viewModel: InventoryViewModel){
    if(viewModel.productList.isNotEmpty()) {
        var sortedProducts = viewModel.productList
        sortedProducts.sortBy { product ->
            SimpleDateFormat("mm/dd/yyyy").parse(product.expirationDate)
        }
        Column(){
            sortedProducts.subList(0, min(4, sortedProducts.size)).forEach{ product ->
                CreateCard(product)
            }
        }
    }
}

//@Composable
//fun SelectButton(
//    @StringRes labelResourceId: Int,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier) {
//      Button(
//            onClick = onClick,
//            modifier = modifier.widthIn(min = 250.dp)
//        ) {
//            Text(stringResource(labelResourceId))
//        }
//
//}

//@Preview
//@Composable
//fun StartPreview(){
//    StartScreen(
//        onNextButtonClicked = {},
//        modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
//    )
//}


