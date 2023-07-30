package ir.miare.androidcodechallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.ActivityMainBinding
import ir.miare.androidcodechallenge.view.viewModel.PlayersViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

  //  viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, RankingFragment(-1))
            .commit()
    }
}
