package org.sopt.and.data.datasource

import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseUserHobbyDto

interface MyDataSource {
    suspend fun getUserHobby(): BaseResponse<ResponseUserHobbyDto>
}
