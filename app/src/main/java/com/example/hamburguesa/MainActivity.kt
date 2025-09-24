

package com.example.hamburguesa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hamburguesa.ui.theme.HamburguesaTheme
import kotlin.random.Random

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
    var bebidaAñadida by remember {
        mutableStateOf(false) }
    var hamburguesasVendidas by remember {
        mutableIntStateOf(0) }
    var bebidasVendidas by remember {
        mutableIntStateOf(0) }
    var juegoTerminado by remember {
        mutableStateOf(false) }

    if (juegoTerminado) {

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable._710),
                contentDescription = "Dinero",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "¡Has vendido 150€!")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {

                dinero = 0
                hamburguesasVendidas = 0
                bebidasVendidas = 0
                estado = 0
                bebidaAñadida = false
                juegoTerminado = false
            }) {
                Text(text = "Reiniciar juego")
            }
        }
    } else {

        val imagen = when (estado) {
            0 -> R.drawable.pan_hamburguesa
            1 -> R.drawable.hamburguesas_timesburg_a_la_parrilla_2_1030x684
            2 -> R.drawable._000_f_308213975_ysr0mkgrminety6rcd3skvwr9tfy33mr
            else -> R.drawable.nci_visuals_food_hamburger
        }

        Column(
            modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()), //para que no colapse
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(160.dp))
            Image(
                painter = painterResource(id = imagen),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))




            Button(onClick = {
                when (estado) {
                    0 -> estado = 1
                    1 -> estado = 2
                    2 -> estado = 3
                    3 -> {
                        dinero += Random.nextInt(8, 15)
                        hamburguesasVendidas++
                        estado = 0
                        if (dinero >= 150) juegoTerminado = true
                    }
                }
            }) {
                if (estado < 3) {

                    Text(text = "Completar hamburguesa")
                  } else {
                    Text(text = "Vender") }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                bebidaAñadida = true
                dinero += 4
                bebidasVendidas++
                if (dinero >= 150) juegoTerminado = true
            }) {
                Text("Añadir bebida (4€)")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (bebidaAñadida) {
                Image(
                    painter = painterResource(id = R.drawable.bebidas),
                    contentDescription = "Bebida",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Dinero ganado: €$dinero")
            Text(text = "Hamburguesas vendidas: $hamburguesasVendidas")
            Text(text = "Bebidas vendidas: $bebidasVendidas")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HamburguesaTheme {
        Greeting()
    }
}

