package com.rafial.invento.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rafial.invento.googleAuth.SignInState


//=============================================================================================
//@Destination(start = true)
@Composable
fun LoginScreen( state: SignInState,
                 authViewModel: AuthViewModel = hiltViewModel(),
//                 navigator: DestinationsNavigator,
                onSignInClick: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state. signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val loggedIn = authViewModel.loggedIn.observeAsState()
    LazyColumn(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = email,
                    leadingIcon =
                    { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
                    shape = RoundedCornerShape(100.dp),
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    placeholder = { Text(text = "Enter your email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = !email.isValidEmail(),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true
                )
                if(email.isNotEmpty()){
                    if (!email.isValidEmail()) {
                        Text(
                            text = "Invalid email format!",
                            fontSize = 14.sp,
                            color = Color(0xFFE26262),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                OutlinedTextField(
                    value = password,
                    leadingIcon = { Icon(imageVector = Icons.Default.Password,
                        contentDescription = "passIcon") },
                    shape = RoundedCornerShape(100.dp),
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    placeholder = { Text(text = "Enter your password") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility)
                                    Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { /* Navigate to register page */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB9FDF5)),
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login",color = Color.Black)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = onSignInClick ,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB9FDF5)),
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login Google",color = Color.Black)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Didn't have an account?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { /* Perform login action */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    border = BorderStroke(5.dp,Color(0xFFB9FDF5))
                ) {
                    Text("Register",color = Color.Black)
                }
            }
        }
    }

}

// Function to validate email format
fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

//@Composable
//@Preview(showBackground = true)
//fun LoginScreenPreview(){
//    LoginScreen(navController = rememberNavController(), state  )
//}
//==========================================================================================
//@Composable
//fun LoginScreen(navController: NavHostController) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        ClickableText(
//            text = AnnotatedString("Sign up here"),
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(20.dp),
//            onClick = {navController.navigate(Routes.RegisterScreen.route) },
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily.Default,
//                textDecoration = TextDecoration.Underline,
//                color = Purple700
//            )
//        )
//    }
//    Column(
//        modifier = Modifier.padding(20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        val username = remember { mutableStateOf(TextFieldValue()) }
//        val password = remember { mutableStateOf(TextFieldValue()) }
//
//        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))
//
//        Spacer(modifier = Modifier.height(20.dp))
//        TextField(
//            label = { Text(text = "Username") },
//            value = username.value,
//            onValueChange = { username.value = it })
//
//        Spacer(modifier = Modifier.height(20.dp))
//        TextField(
//            label = { Text(text = "Email") },
//            value = password.value,
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            onValueChange = { password.value = it })
//
//        Spacer(modifier = Modifier.height(20.dp))
//        TextField(
//            label = { Text(text = "Password") },
//            value = password.value,
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            onValueChange = { password.value = it })
//
//        Spacer(modifier = Modifier.height(20.dp))
//        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//            Button(
//                onClick = { },
//                shape = RoundedCornerShape(50.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//            ) {
//                Text(text = "Login")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//        ClickableText(
//            text = AnnotatedString("Forgot password?"),
//            onClick = {  navController.navigate(Routes.ForgotPassword.route)},
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily.Default
//            )
//        )
//    }
//}