package tj.soni.z

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Dark = Color(0xFF101A22)
private val Card = Color(0xFF1D2B36)
private val Green = Color(0xFF35C98A)
private val White = Color.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var command by remember { mutableStateOf("") }
            var status by remember {
                mutableStateOf("Z омода аст!")
            }

            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Dark)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(35.dp))

                    Text(
                        text = "Z",
                        color = Green,
                        fontSize = 64.sp
                    )

                    Text(
                        text = "YOUR SMART ASSISTANT",
                        color = White,
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.height(35.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Card
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                "Ҳолати Z",
                                color = Green,
                                fontSize = 20.sp
                            )

                            Spacer(Modifier.height(10.dp))

                            Text(
                                status,
                                color = White
                            )
                        }
                    }

                    Spacer(Modifier.height(25.dp))

                    Button(
                        onClick = {
                            startActivity(
                                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green
                        )
                    ) {
                        Text(
                            "Фаъол кардани Accessibility",
                            color = Dark
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    Text(
                        "Фармони экран",
                        color = White,
                        fontSize = 18.sp
                    )

                    Spacer(Modifier.height(10.dp))

                    val commands = listOf(
                        "back" to "Бозгашт",
                        "home" to "Экрани асосӣ",
                        "notifications" to "Огоҳиномаҳо",
                        "quick_settings" to "Танзимоти зуд"
                    )

                    commands.forEach { (value, label) ->
                        Button(
                            onClick = {
                                val success =
                                    ZAccessibilityService.execute(value)

                                status = if (success) {
                                    "Фармон иҷро шуд: $label"
                                } else {
                                    "Accessibility-ро фаъол кун."
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Card
                            )
                        ) {
                            Text(label, color = White)
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    OutlinedTextField(
                        value = command,
                        onValueChange = { command = it },
                        label = {
                            Text("Фармон: back, home")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            status = if (
                                ZAccessibilityService.execute(command.trim())
                            ) {
                                "Фармон иҷро шуд."
                            } else {
                                "Фармон иҷро нашуд."
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Иҷро кардан")
                    }
                }
            }
        }
    }
}
