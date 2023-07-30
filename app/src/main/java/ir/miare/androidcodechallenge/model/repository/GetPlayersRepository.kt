package ir.miare.androidcodechallenge.model.repository

import io.reactivex.Observable
import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.utiles.ResultWrapper

interface GetPlayersRepository {
    fun getData(): Observable<ResultWrapper<List<FakeData>>>
}