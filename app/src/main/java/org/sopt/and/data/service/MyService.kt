package org.sopt.and.data.service

import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseUserHobbyDto
import retrofit2.http.GET

interface MyService {
    @GET("/user/my-hobby")
    suspend fun getUserHobby(): BaseResponse<ResponseUserHobbyDto>
}
