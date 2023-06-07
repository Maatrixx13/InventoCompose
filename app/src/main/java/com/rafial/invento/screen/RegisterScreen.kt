package com.rafial.invento.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


//=============================================================================================
//@Destination
@Composable
fun RegisterScreen(navController: NavHostController) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    LazyColumn(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally){
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
//        USERNAME FIELD
                OutlinedTextField(
                    value = username,
                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "nameIcon") },
                    shape = RoundedCornerShape(100.dp),
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    placeholder = { Text(text = "Enter your username") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true
                )


                Spacer(modifier = Modifier.height(2.dp))
//        EMAIL FIELD
                OutlinedTextField(
                    value = email,
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
                    shape = RoundedCornerShape(100.dp),
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    placeholder = { Text(text = "Enter your e-mail") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = !email.isValidEmailReg(),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true
                )
                if(email.isNotEmpty()){
                    if (!email.isValidEmailReg()) {
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
                    leadingIcon = { Icon(imageVector = Icons.Default.Password, contentDescription = "passIcon") },
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
                    Text("Register",color = Color.Black)
                }

                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Already have an account?",
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
                    Text("Login",color = Color.Black)
                }
            }
        }
    }

}

// Function to validate email format
fun String.isValidEmailReg(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview(){
    RegisterScreen(navController = rememberNavController())
}