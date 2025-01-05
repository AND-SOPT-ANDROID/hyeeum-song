package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.dto.request.toDto
import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.response.ResponseSignInEntity
import org.sopt.and.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : SignInRepository {
    override suspend fun signIn(body: RequestSignInEntity): Result<ResponseSignInEntity> =
        runCatching {
            authDataSource.postSignIn(body.toDto()).result.toEntity()
        }
}
