package com.app.git.di.network

import com.app.git.data.network.GitNetwork
import com.app.git.data.repository.GitRepositoryImp
import com.app.git.domain.usecases.GitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        network: GitNetwork
    ): GitRepository {
        return GitRepositoryImp(
            network = network
        )
    }

}