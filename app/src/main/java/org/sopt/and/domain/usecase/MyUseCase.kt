package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.response.ResponseHobbyEntity
import org.sopt.and.domain.repository.MyRepository
import javax.inject.Inject

class MyUseCase @Inject constructor(
    private val myRepository: MyRepository
) {
    suspend operator fun invoke(): Result<ResponseHobbyEntity> =
        myRepository.getHobby()
}
