package com.example.integrationmvp.screen.screens

import com.example.integrationmvp.R
import com.example.integrationmvp.ui.theme.Azul4
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul5

@Composable
fun BemVindo(navController: NavController) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nome do aplicativo
        Text(
            text = "MedConnect",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Azul4,
            modifier = Modifier
                .padding(bottom = 46.dp)

        )

        // Imagem central
        Image(
            painter = painterResource(R.drawable.diagnostico),
            contentDescription = "Logo do aplicativo",
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Fit
        )

        // Botão "Entrar"
        Button(
            onClick = {
                navController.navigate("Menu")
            },
            colors = ButtonDefaults.buttonColors(Azul4),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .shadow(10.dp, shape = RoundedCornerShape(50.dp))
                .width(250.dp),
        ) {
            Text(
                text = "ENTRAR",
                fontSize = 20.sp,
                color = Azul1
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}


@Preview(showBackground = true)
@Composable
fun BemVindoPreview() {
    BemVindo(navController = rememberNavController())
}


