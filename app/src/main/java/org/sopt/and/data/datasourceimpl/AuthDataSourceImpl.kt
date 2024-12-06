package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.dto.request.RequestSignInDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto> =
        authService.postSignUp(requestSignUpDto)

    override suspend fun postSignIn(requestSignInDto: RequestSignInDto): BaseResponse<ResponseSignInDto> =
        authService.postSignIn(requestSignInDto)
}
