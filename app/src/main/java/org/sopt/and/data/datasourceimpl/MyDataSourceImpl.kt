package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.MyDataSource
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.ResponseUserHobbyDto
import org.sopt.and.data.service.MyService
import javax.inject.Inject

class MyDataSourceImpl @Inject constructor(
    private val myService: MyService
) : MyDataSource {
    override suspend fun getUserHobby(): BaseResponse<ResponseUserHobbyDto> =
        myService.getUserHobby()
}
