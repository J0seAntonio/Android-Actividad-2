package com.example.catalogoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catalogoapp.ui.theme.CatalogoAppTheme

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val estatus: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CatalogoAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(navController = navController)
                    }
                    composable("list_tasks") {
                        ListTasksScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "Bienvenido",
                    fontSize = 28.sp,
                    color = Color(0xFF4A4A4A),
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Tasks app",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1E1E1E)
                )

                Spacer(modifier = Modifier.height(48.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = "Logo",
                        tint = Color(0xFFBDBDBD),
                        modifier = Modifier.size(110.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    text = "Correo electrónico",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("ejemplo@nomail.com", color = Color(0xFFAAAAAA)) },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF1E1E1E)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Contraseña",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("*******", color = Color(0xFFAAAAAA)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF1E1E1E)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        navController.navigate("list_tasks")
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2B2B2B),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTasksScreen(navController: NavController) {
    val tareas = remember {
        mutableStateListOf(
            Tarea(1, "Aprender Android", "Empezar mi certificación de Aspectos básicos en Android con Compose en google developer program", "Pendiente"),
            Tarea(2, "Hacer Actividad 2", "Desarrollar la app de listado de tareas, con estatus pendiente, completados. Deberan poder eliminar tareas y quitar visualmente del listado, además...", "Completada"),
            Tarea(3, "Aprender Android", "Empezar mi certificación de Aspectos básicos en Android con Compose en google developer program", "Completada"),
            Tarea(4, "Diseñar UI en Figma", "Crear la paleta de colores y componentes visuales para la aplicación móvil.", "Pendiente"),
            Tarea(5, "Configurar Room Database", "Implementar la persistencia de datos local para almacenar tareas.", "Pendiente"),
            Tarea(6, "Implementar ViewModel", "Separar la lógica de negocio de la capa de interfaz de usuario.", "Completada"),
            Tarea(7, "Revisar Guías de Material 3", "Consultar especificaciones oficiales de componentes y tipografía.", "Pendiente"),
            Tarea(8, "Agregar Navegación", "Configurar NavHost y rutas entre pantallas principales.", "Completada"),
            Tarea(9, "Subir repositorio a GitHub", "Crear el README y subir el código con ramas ordenadas.", "Completada"),
            Tarea(10, "Validar Formularios", "Asegurar que los campos no se envíen vacíos en el login.", "Pendiente"),
            Tarea(11, "Optimizar LazyColumn", "Agregar claves únicas con el parámetro key para rendimiento fluido.", "Completada"),
            Tarea(12, "Configurar Temas y Colores", "Definir modo claro y modo oscuro en Theme.kt.", "Pendiente"),
            Tarea(13, "Escribir Pruebas Unitarias", "Probar funciones lógicas y modelos de datos con JUnit.", "Pendiente"),
            Tarea(14, "Configurar Iconos Extendidos", "Añadir dependencia de Material Icons al archivo build.gradle.", "Completada"),
            Tarea(15, "Revisar Memory Leaks", "Monitorear el uso de memoria en Android Profiler.", "Pendiente"),
            Tarea(16, "Añadir Animaciones", "Animar la eliminación de elementos en la lista de Compose.", "Pendiente"),
            Tarea(17, "Probar en Tablet", "Verificar comportamiento de pantalla adaptativa y orientación.", "Pendiente"),
            Tarea(18, "Limpiar Código", "Eliminar imports sin usar y dar formato al archivo con Kotlin style.", "Completada"),
            Tarea(19, "Generar Release APK", "Compilar el paquete final firmado para pruebas de distribución.", "Pendiente"),
            Tarea(20, "Entrega Final", "Subir capturas y enlace del proyecto a la plataforma escolar.", "Pendiente")
        )
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Inicio",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1E1E1E)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar al Login",
                            tint = Color(0xFF1E1E1E)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Mis tareas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1E),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            if (tareas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Oops, te quedaste sin tareas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(
                        items = tareas,
                        key = { tarea -> tarea.id }
                    ) { tarea ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF7F7F7)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = tarea.titulo,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E1E1E)
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = tarea.descripcion,
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp,
                                    color = Color(0xFF6B6B6B),
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Status: ${tarea.estatus}",
                                        fontSize = 13.sp,
                                        color = Color(0xFF7A7A7A),
                                        fontWeight = FontWeight.Normal
                                    )

                                    IconButton(
                                        onClick = { tareas.remove(tarea) },
                                        modifier = Modifier
                                            .size(38.dp)
                                            .background(Color(0xFF2E2E2E), CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Eliminar",
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}