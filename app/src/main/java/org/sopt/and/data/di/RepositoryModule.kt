package org.sopt.and.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.repositoryimpl.MyRepositoryImpl
import org.sopt.and.data.repositoryimpl.SignInRepositoryImpl
import org.sopt.and.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.and.domain.repository.MyRepository
import org.sopt.and.domain.repository.SignInRepository
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsSignInRepository(
        signInRepositoryImpl: SignInRepositoryImpl
    ): SignInRepository

    @Binds
    @Singleton
    abstract fun bindsSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository

    @Binds
    @Singleton
    abstract fun bindsMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository
}
