package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.WavveDataSource
import org.sopt.and.data.dto.request.RequestSignInDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseHobbyDto
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.data.service.WavveService
import javax.inject.Inject

class WavveDataSourceImpl @Inject constructor(
    val wavveService: WavveService
) : WavveDataSource {
    override suspend fun postUser(requestSignUpDto: RequestSignUpDto): BaseResponse<ResponseSignUpDto> =
        wavveService.signUp(requestSignUpDto)

    override suspend fun getUser(requestSignInDto: RequestSignInDto): BaseResponse<ResponseSignInDto> =
        wavveService.signIn(requestSignInDto)

    override suspend fun getHobby(): BaseResponse<ResponseHobbyDto> =
        wavveService.getHobby()

}
