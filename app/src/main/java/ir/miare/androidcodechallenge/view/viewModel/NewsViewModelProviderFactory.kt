package ir.miare.androidcodechallenge.view.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.miare.androidcodechallenge.model.repository.GetPlayersRepository

class NewsViewModelProviderFactory(
    private val repository: GetPlayersRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersViewModel(repository) as T
    }
}