package ir.miare.androidcodechallenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.model.repository.GetPlayersRepository
import ir.miare.androidcodechallenge.model.repository.GetPlayersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideApiRepository(getPlayersRepositoryImpl: GetPlayersRepositoryImpl): GetPlayersRepository
}
