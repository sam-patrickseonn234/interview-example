package ir.miare.androidcodechallenge.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.model.repository.GetPlayersRepository
import ir.miare.androidcodechallenge.utiles.ResultWrapper
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val getPlayersRepository: GetPlayersRepository,
) : ViewModel() {
    private val _allPlayers: MutableLiveData<ResultWrapper<List<FakeData>>> = MutableLiveData()
    val allPlayers: LiveData<ResultWrapper<List<FakeData>>> = _allPlayers

    init {
        getAllPlayers()
    }

    private fun getAllPlayers() {
        viewModelScope.launch {
            val disposable: Disposable = getPlayersRepository.getData()
                .subscribe { data ->
                    _allPlayers.postValue(data)
                }
        }
    }
}
