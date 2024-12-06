package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.response.ResponseHobbyEntity
import org.sopt.and.domain.repository.WavveRepository
import javax.inject.Inject

class MyUseCase @Inject constructor(
    private val myRepository: WavveRepository
) {
    suspend operator fun invoke(): Result<ResponseHobbyEntity> =
        myRepository.getHobby()
}
