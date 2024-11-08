package org.sopt.and.data.datasource

import org.sopt.and.data.dto.request.RequestSignInDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseHobbyDto
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto

interface WavveDataSource {
    suspend fun postUser(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto>
    suspend fun getUser(requestSignInDto: RequestSignInDto): BaseResponse<ResponseSignInDto>
    suspend fun getHobby(): BaseResponse<ResponseHobbyDto>
}
