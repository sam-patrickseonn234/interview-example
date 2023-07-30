package ir.miare.androidcodechallenge.model.remote

import io.reactivex.Observable
import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.utiles.ResultWrapper
import retrofit2.http.GET

interface Api {
    @Mock("data.json")
    @GET("list")
    fun getData(): Observable<ResultWrapper<List<FakeData>>>
}
