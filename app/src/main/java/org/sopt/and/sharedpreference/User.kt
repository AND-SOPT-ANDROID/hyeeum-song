package org.sopt.and.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class User @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveUserToken(token: String) {
        with(sharedPreferences.edit()) {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString(TOKEN, null)
    }

    fun clearSignInState() {
        with(sharedPreferences.edit()) {
            putBoolean(SIGNIN_STATE, false)
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
        private const val PREFERENCE_NAME: String = "user"
        const val TOKEN: String = "token"
        private const val SIGNIN_STATE: String = "signin_state"
    }
}
