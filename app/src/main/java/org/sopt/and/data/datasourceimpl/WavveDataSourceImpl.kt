package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.WavveDataSource
import org.sopt.and.data.dto.request.RequestSignInDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseUserHobbyDto
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.data.service.WavveService
import javax.inject.Inject

class WavveDataSourceImpl @Inject constructor(
    private val wavveService: WavveService
) : WavveDataSource {
    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto> =
        wavveService.postSignUp(requestSignUpDto)

    override suspend fun postSignIn(requestSignInDto: RequestSignInDto): BaseResponse<ResponseSignInDto> =
        wavveService.postSignIn(requestSignInDto)

    override suspend fun getUserHobby(): BaseResponse<ResponseUserHobbyDto> =
        wavveService.getUserHobby()

}
