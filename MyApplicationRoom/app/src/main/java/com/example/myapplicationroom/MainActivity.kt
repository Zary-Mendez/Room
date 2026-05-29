package com.example.myapplicationroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationroom.database.viewModels.UserViewModel
import com.example.myapplicationroom.ui.theme.MyApplicationRoomTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by lazy{ UserViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            this.userViewModel.saveUser(User(id = 0, name = "Prueba", pwd = "123")){
                if(it){
                    println("Usuario guardado")
                }
            }
            MyApplicationRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationRoomTheme {
        Greeting("Android")
    }
}