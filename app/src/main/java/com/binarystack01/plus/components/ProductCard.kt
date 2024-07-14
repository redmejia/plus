package com.binarystack01.plus.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binarystack01.plus.R
import com.binarystack01.plus.ui.theme.black50
import com.binarystack01.plus.ui.theme.blue400
import com.binarystack01.plus.ui.theme.blue500

@Composable
fun ProductCard() {
    Card(
        modifier = Modifier
            .padding(2.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        ),
        border = BorderStroke(1.dp, color = blue400),
        onClick = { /*TODO*/ }
    ) {
        Box {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.cherry),
                contentDescription = "cherry"
            )
            FloatingContent(modifier = Modifier.matchParentSize())
        }
    }
}

@Composable
fun FloatingContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    text = "112"
                )
            }
        }
        Column(
            modifier = Modifier
                .background(color = black50)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    ) {
                        append("Product Name")
                    }
                    append("\n")
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        )
                    ) {
                        append("$12")
                    }
                },
                modifier = Modifier
                    .padding(3.dp),
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.50f),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue400
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.remove_icon),
                    contentDescription = "remove"
                )
            }
            Spacer(modifier = Modifier.width(1.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue500
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    }
}

data class DummyData(val name: String, val lastName: String)
@Preview(showBackground = true)
@Composable
fun OrderCardPreview() {
    val users: List<DummyData> = listOf(
        DummyData(name = "Re", lastName = "me"),
        DummyData(name = "ee", lastName = "e"),
        DummyData(name = "ee", lastName = "e"),
    )
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
//        MyCard()


        LazyVerticalGrid(
            modifier = Modifier.padding(2.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(users) { item ->
//                Log.d("ITEM", "OrderCardPreview: $item")
                ProductCard()
//                OrderCard()

            }
        }
    }
}