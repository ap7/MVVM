package com.softbankrobotics.mvvm.ui.auth

// to get the call back from the view, we need to create an interface
// the goal is to display the error or success message when the user validate his login & password
interface AuthListener {

    // the login is a network operation
    // display progressbar to the user to know whe this operation has started
    fun onStarted()

    // when the authentication is successful
    fun onSuccess()

    // when the operation fails we need to get the reason of this failure
    fun onFailure(reason: String)
}