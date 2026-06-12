package com.devpro.android58_day12.di

import com.devpro.android58_day12.data.repository.UserRepository
import com.devpro.android58_day12.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    /**
     * Hilt @Binds: liên kết interface UserRepository với implementation UserRepositoryImpl.
     * Hilt sẽ tự inject UserRepositoryImpl bất cứ khi nào có @Inject UserRepository.
     */
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}

