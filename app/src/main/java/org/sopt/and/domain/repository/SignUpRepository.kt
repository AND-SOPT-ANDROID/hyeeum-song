package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.entity.response.ResponseSignUpEntity

interface SignUpRepository {
    suspend fun signUp(body: RequestSignUpEntity): Result<ResponseSignUpEntity>
}
