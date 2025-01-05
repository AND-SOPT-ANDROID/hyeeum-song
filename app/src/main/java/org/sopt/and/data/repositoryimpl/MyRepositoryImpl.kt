package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.MyDataSource
import org.sopt.and.domain.entity.response.ResponseHobbyEntity
import org.sopt.and.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myDataSource: MyDataSource
) : MyRepository {
    override suspend fun getHobby(): Result<ResponseHobbyEntity> = runCatching {
        myDataSource.getUserHobby().result.toEntity()
    }
}
