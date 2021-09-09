package ca.ispy.profilecardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.ispy.profilecardcompose.ui.theme.ProfileCardComposeTheme
import ca.ispy.profilecardcompose.ui.theme.lightGreen
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ProfileCardComposeTheme {
                MainScreen()
            }

        }
    }
}

@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(topBar = { AppBar() }) {
        androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn() {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile)
                }
            }

        }
    }
    
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                Icons.Default.Home,
                contentDescription = "content description",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
                         },
        title = { Text(text = "Profile Cards")}
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }

    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        CompositionLocalProvider(
            LocalContentAlpha provides (
                    if (onlineStatus)
                        1f else ContentAlpha.medium)
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.h5
            )
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) { //add an alpha on text or anything
            Text(
                text = if (onlineStatus)"Active now" else "Offline",
                style = MaterialTheme.typography.body2
            )
        }

    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus) {
                MaterialTheme.colors.lightGreen
            } else {
                Color.Red
            }
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(painter = rememberCoilPainter( //coil
            request = pictureUrl,
            requestBuilder = {
                transformations(CircleCropTransformation())
            }),
            contentDescription = "Profile picture",
            modifier = Modifier.size(72.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileCardComposeTheme {
        MainScreen()
    }
}