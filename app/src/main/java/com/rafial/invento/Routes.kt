package com.rafial.invento

sealed class Routes(val route:String) {
    object LoginScreen : Routes("Login")
    object RegisterScreen : Routes("Register")
    object ForgotPassword:Routes("Forgot Password")
}
