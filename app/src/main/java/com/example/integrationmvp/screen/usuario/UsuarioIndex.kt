package com.example.integrationmvp.screen.usuario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.integrationmvp.component.BottomNavigation
import com.example.integrationmvp.model.UsuarioModel
import com.example.integrationmvp.ui.theme.Azul1
import com.example.integrationmvp.ui.theme.Azul4
import com.example.integrationmvp.viewModel.UsuarioViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioIndex(navController: NavController) {
    val usuarioView =  UsuarioViewModel()
    val usuarios = usuarioView.getUsuarios()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Usuarios",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Azul4,
                modifier = Modifier
                    .padding(bottom = 46.dp)

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Button( // Button in the column
                    onClick = { navController.navigate("UsuarioCadastro") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )

                ) {
                    Text(text = "Novo Usuário")
                }

                if (usuarios.isNotEmpty()) {
                    usuarios.forEach { usuario ->
                        UsuarioCard(
                            usuario = usuario,
                            onEditClick = { navController.navigate("usuarioatualizar")},
                            onDeleteClick = { navController.navigate("usuarioexcluir") },
                            onChangeClick = { navController.navigate("usuarioconsultar") }
                        )
                    }
                } else {
                    Text(
                        text = "Nenhum usuário encontrado.",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                // Spacer to push content up
                Spacer(modifier = Modifier.height(16.dp)) // Add some space
            }
        }

        BottomNavigation(
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()

        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioCard(
    usuario: UsuarioModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onChangeClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column( // Wrap content with a Column
            modifier = Modifier
                .background(Azul1) // Set text color for content inside
        ) {

            Text(text = "Nome: ${usuario.nomeUsuario}")
            Text(text = "Senha: ${usuario.senhaUsuario}")
            Text(text = "Email: ${usuario.emailUsuario}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onEditClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Consultar")

                }
                Button(onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Atualizar")
                }
                Button(onClick = onChangeClick,
                    colors = ButtonDefaults.buttonColors(
                        Azul4,
                        contentColor = Azul1
                    )) {
                    Text(text = "Excluir")

                }
            }
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UsuarioIndexPreview() {
    val navController = rememberNavController()
    UsuarioIndex(navController = navController)
}
