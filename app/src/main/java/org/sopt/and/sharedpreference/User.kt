package org.sopt.and.sharedpreference

import android.content.Context
import android.content.SharedPreferences

class User(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveUserInformation(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putString(EMAIL, email)
            putString(PASSWORD, password)
            apply()
        }
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(EMAIL, null)
    }

    fun getPassword(): String? {
        return sharedPreferences.getString(PASSWORD, null)
    }

    fun clearUserInformation() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    fun setSignInState(isLoggedIn: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(SIGNIN_STATE, isLoggedIn)
            apply()
        }
    }

    fun getSignInState(): Boolean {
        return sharedPreferences.getBoolean(SIGNIN_STATE, false)
    }

    companion object {
        private const val PREFERENCE_NAME: String = "User"
        const val EMAIL: String = "user_email"
        const val PASSWORD: String = "user_password"
        private const val SIGNIN_STATE: String = "signin_state"
    }
}
