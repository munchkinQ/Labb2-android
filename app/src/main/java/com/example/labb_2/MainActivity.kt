package com.example.labb_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labb_2.ui.theme.DarkGreen
import com.example.labb_2.ui.theme.Labb2Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import com.example.labb_2.ui.theme.ColdBeige
import com.example.labb_2.ui.theme.WarmGreen
import com.example.labb_2.ui.theme.SkyBlue



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labb2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Preview
@Composable
fun MyApp() {


    val buttonColor = Color.Yellow
    val navController = rememberNavController()

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = DarkGreen
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {Home(navController)}
            composable("login") {Login(navController, null, null)}
            composable("about") {About(navController)}
            composable("end/{username}") {navBackStackEntry ->
                val username = navBackStackEntry.arguments?.getString("username") ?: ""
                End(navController, username)
            }

            composable (
                "loginPage/{username}/{password}",
                arguments = listOf (
                    navArgument("username") {type = NavType.StringType},
                    navArgument("password") {type = NavType.StringType}
                )
            ) {navBackStackEntry ->
                val username = navBackStackEntry.arguments?.getString("username") ?: ""
                val password = navBackStackEntry.arguments?.getString("password") ?: ""
                Login(navController, username, password)

            }
        }
    }
}

@Composable
fun Home(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Welcome!",
                fontSize = 30.sp,
                color = ColdBeige,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)

            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {

            Text(text = "At first I didn't know what theme I wanted this project to have, no matter how hard I tried. So I asked my sisters what my app should be about, and they decided that dinosaurs was the only right way. So here's my app with the theme: Dinosaurs. Enjoy! <3",
                fontSize = 20.sp,
                color = ColdBeige,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                )
    }

        Button(onClick = {navController.navigate("about")},
            colors = ButtonDefaults.buttonColors(WarmGreen),
            modifier = Modifier
                .padding(20.dp)) {
            Text(text = "To About")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.cool_trex_semicircle),
            contentDescription = "T-rex",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}


@Composable
fun Login(navController: NavController, username: String?, password: String?) {
    var userNameValue by remember {mutableStateOf(username ?: "")}
    var passwordValue by remember {mutableStateOf(password ?: "")}


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Login",
            fontSize = 24.sp,
            color = ColdBeige,
            modifier = Modifier
                .padding(16.dp)
        )

        TextField(
            value = userNameValue,
            onValueChange = { userNameValue = it },
            label = { Text("Username") }
        )
        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text("Password") }
        )

        Button(onClick = {
            if (userNameValue in dinosaurs && passwordValue == "12345") {
                navController.navigate("end/$userNameValue")
            }
        },
            modifier = Modifier.padding(32.dp),
            colors = ButtonDefaults.buttonColors(WarmGreen)

        ) {
            Text(text="Login")
        }
    }
}


@Composable
fun About(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SkyBlue) {

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "About the App",
            fontSize = 24.sp,
            color = WarmGreen,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Text(text = "On the next slide you will be prompted to login with a username, this username will be any dinosaur* of your choice! So make sure to pick well, now is your chance to think of your favourite**",
            fontSize = 18.sp,
            color = WarmGreen,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Text(text = "*out of a list of the following: T-rex, Stegosaurus, Triceratops," +
                "Brontosaurus, Brachiosaurus, Brontosaurus," +
                "Tyrannosaurus, Spinosaurus, Pterodactyl" +
                "**as long as your favourite is one of the aforementioned dinosaurs",
            fontSize = 10.sp,
            color = WarmGreen,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Button(onClick = {navController.navigate("login")},
            colors = ButtonDefaults.buttonColors(WarmGreen)) {
            Text(text = "To Login")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.cute_longnecks_in_water),
            contentDescription = "Longnecks",
            modifier = Modifier
                .fillMaxWidth()
                .height(222.dp)
        )
    }
}

@Composable
fun End(navController: NavController, username: String) {

    Column(
        horizontalAlignment = Alignment.Start

    ) {

        Text(text = "Welcome $username !",
            fontSize = 30.sp,
            color = ColdBeige,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Text(text = "$username ! What a good choice of dinosaur! Exactly the one I would've picked, too, of course. In any case, this is the end of my application, hope you have enjoyed this short presentation. As some last and final words, here is my *actual* favourite dinosaur: Mei Long, meaning sleeping dragon. It was a very small dinosaur whose fossil was found in China, it was alive during the Cretaceous period.",
            fontSize = 22.sp,
            color = ColdBeige,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Button(onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(WarmGreen),
            /*modifier = Modifier
                .padding(24.dp)*/) {
            Text(text = "Back Home")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.brachio_eating),
            contentDescription = "eating dino",
            modifier = Modifier
                .align(Alignment.Start)
        )
    }
}

val dinosaurs = arrayOf(
    "T-rex", "Stegosaurus", "Triceratops",
    "Brontosaurus", "Brachiosaurus", "Brontosaurus",
    "Tyrannosaurus", "Spinosaurus", "Pterodactyl"
)