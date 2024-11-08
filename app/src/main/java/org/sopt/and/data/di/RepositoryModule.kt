package org.sopt.and.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.repositoryimpl.WavveRepositoryImpl
import org.sopt.and.domain.repository.WavveRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsRepository(
        myRepositoryImpl: WavveRepositoryImpl
    ): WavveRepository
}
