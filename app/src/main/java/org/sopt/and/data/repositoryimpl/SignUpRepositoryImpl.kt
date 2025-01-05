package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.dto.request.toDto
import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.ResponseSignUpEntity
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : SignUpRepository {
    override suspend fun signUp(body: RequestSignUpEntity): Result<ResponseSignUpEntity> =
        runCatching {
            authDataSource.postSignUp(body.toDto()).result.toEntity()
        }
}
