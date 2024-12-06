package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.entity.response.ResponseSignInEntity

interface SignInRepository {
    suspend fun signIn(body: RequestSignInEntity): Result<ResponseSignInEntity>
}
