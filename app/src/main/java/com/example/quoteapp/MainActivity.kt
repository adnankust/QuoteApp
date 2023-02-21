package com.example.quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quoteapp.data.Datasource
import com.example.quoteapp.model.Affirmation
import com.example.quoteapp.ui.theme.AffirmationTheme
import com.example.quoteapp.ui.theme.QuoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AffirmationApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationApp() {
    AffirmationTheme {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {

    LazyColumn {
        items(affirmationList) {
                affirmation -> AffirmationCard(affirmation)

        }
    }

}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
      Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {

          Column() {

              Image(painter = painterResource(affirmation.imageResourceId),
                  contentDescription = stringResource(affirmation.stringResourceId),
              modifier = Modifier.fillMaxWidth().height(194.dp),
                          contentScale = ContentScale.Crop)
              Text(
                  text = LocalContext.current.getString(affirmation.stringResourceId),
                         modifier = Modifier.padding(16.dp),
                         style = MaterialTheme.typography.h6
              )

          }

      }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuoteAppTheme {
        AffirmationApp()
    }
}