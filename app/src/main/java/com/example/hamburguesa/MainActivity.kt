

package com.example.hamburguesa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hamburguesa.ui.theme.HamburguesaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HamburguesaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}




@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var estado by remember {
        mutableIntStateOf(0) }
    var dinero by remember {
        mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        val imagen = when (estado) {
            0 -> R.drawable.pan_hamburguesa
            1 -> R.drawable.hamburguesas_timesburg_a_la_parrilla_2_1030x684
            2 -> R.drawable._000_f_308213975_ysr0mkgrminety6rcd3skvwr9tfy33mr
            else ->  R.drawable.nci_visuals_food_hamburger
        }

        Image(
            painter = painterResource(id = imagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = {
            when (estado) {
                0 -> estado = 1
                1 -> estado = 2
                2 -> estado = 3
                3 -> {
                    dinero += 15
                    estado = 0
                }
            }

        })
        {

            if (estado < 3){
                Text(text = "Completar hamburguesa")

            } else {
                Text(text = "Vender")
            }
        }









        Spacer(modifier = Modifier.height(20.dp))


        Text(
            text = "Dinero ganado: €$dinero",
            style = MaterialTheme.typography.bodyLarge
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HamburguesaTheme {
        Greeting()
    }
}

