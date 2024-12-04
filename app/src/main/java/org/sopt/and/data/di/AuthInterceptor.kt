package org.sopt.and.data.di

import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.and.sharedpreference.User
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val user: User) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = user.getUserToken()

        val request = chain.request().newBuilder()
            .apply {
                token?.let {
                    addHeader(TOKEN, token)
                }
            }
            .build()

        return chain.proceed(request)
    }

    companion object {
        const val TOKEN: String = "token"
    }
}
