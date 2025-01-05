package org.sopt.and.data.service

import org.sopt.and.data.dto.request.RequestSignInDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/user")
    suspend fun postSignUp(
        @Body body: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>

    @POST("/login")
    suspend fun postSignIn(
        @Body body: RequestSignInDto
    ): BaseResponse<ResponseSignInDto>
}
