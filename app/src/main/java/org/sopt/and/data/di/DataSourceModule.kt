package org.sopt.and.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.datasource.MyDataSource
import org.sopt.and.data.datasourceimpl.AuthDataSourceImpl
import org.sopt.and.data.datasourceimpl.MyDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindsMyDataSource(MyDataSourceImpl: MyDataSourceImpl): MyDataSource
}

