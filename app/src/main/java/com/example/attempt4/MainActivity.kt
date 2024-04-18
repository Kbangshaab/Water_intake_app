package com.example.attempt4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.attempt4.ui.theme.Attempt4Theme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts.db"
        ).build()
    }
    private val viewModel by viewModels<ContactViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewModel(db.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Attempt4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val navController = rememberNavController()
                    Navigation(navController = navController, viewModel)
                }

            }
        }
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: ContactViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreenOptions.route) {
        composable(route = Screen.HomeScreenOptions.route){
            HomeScreenOptions(navController = navController)
        }
        composable(route = Screen.WaterScreen.route){
            val state by viewModel.state.collectAsState()
            WaterScreen(navController = navController, state = state, onEvent = viewModel::onEvent)

        }
        composable(route = Screen.CaloriesScreen.route){
            CaloriesScreen(navController = navController)
        }
        composable(route = Screen.WeightScreen.route){
            WeightScreen(navController = navController)
        }
    }
}

@Composable
fun HomeScreenOptions(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable { navController.navigate(Screen.WaterScreen.route) }
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.water_1_),
                    contentDescription = stringResource(R.string.option1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(R.string.option1),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable { navController.navigate(Screen.CaloriesScreen.route) }
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.calories_1_),
                    contentDescription = stringResource(R.string.option2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(R.string.option2),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable { navController.navigate(Screen.WeightScreen.route) }
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.weight_1_),
                    contentDescription = stringResource(R.string.option3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(R.string.option3),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

