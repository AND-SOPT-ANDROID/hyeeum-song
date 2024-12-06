package org.sopt.and.data.datasource

import org.sopt.and.data.dto.request.RequestSignInDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto

interface AuthDataSource {
    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto>
    suspend fun postSignIn(requestSignInDto: RequestSignInDto): BaseResponse<ResponseSignInDto>
}
