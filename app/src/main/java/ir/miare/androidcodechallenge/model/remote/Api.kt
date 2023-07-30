package ir.miare.androidcodechallenge.model.remote

import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.model.model.FakeData
import retrofit2.http.GET

interface Api {
    @Mock("data.json")
    @GET("list")
    suspend fun getData(): List<FakeData>
}
