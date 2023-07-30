package ir.miare.androidcodechallenge.model.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.model.remote.Api
import ir.miare.androidcodechallenge.utiles.ResultWrapper
import java.lang.Exception
import javax.inject.Inject

class GetPlayersRepositoryImpl @Inject constructor(
    private val api: Api,
) : GetPlayersRepository {
    override fun getData(): Observable<ResultWrapper<List<FakeData>>> {
        return api.getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { ResultWrapper.Error(Exception()) }
    }
}
