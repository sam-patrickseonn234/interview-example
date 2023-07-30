package ir.miare.androidcodechallenge.model.repository

import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.model.remote.Api
import ir.miare.androidcodechallenge.utiles.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlayersRepositoryImpl @Inject constructor(
    private val api: Api,
) : GetPlayersRepository {
    override suspend fun getData(): Flow<ResultWrapper<List<FakeData>>> = flow {
        emit(ResultWrapper.Loading)
        try {
            val data = api.getData()
            emit(ResultWrapper.Success(data))
        } catch (e: HttpException) {
            emit(ResultWrapper.Error(e))
        } catch (e: IOException) {
            emit(ResultWrapper.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}
