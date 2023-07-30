package ir.miare.androidcodechallenge.model.repository

import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.utiles.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GetPlayersRepository {
    suspend fun getData(): Flow<ResultWrapper<List<FakeData>>>
}