package ir.miare.androidcodechallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.model.model.FakeData
import ir.miare.androidcodechallenge.view.PlayerInfoBottomSheet

class LeagueAdapter(val requireActivity: FragmentActivity) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    lateinit var playerAdapter: PlayerAdapter
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leagueName: TextView = itemView.findViewById(R.id.league_name)
        val leagueCountry: TextView = itemView.findViewById(R.id.league_country)
        val playerList: RecyclerView = itemView.findViewById(R.id.player_list)
    }
    private val differCallback = object : DiffUtil.ItemCallback<FakeData>() {
        override fun areItemsTheSame(oldItem: FakeData, newItem: FakeData): Boolean {
            return oldItem.league.name == newItem.league.name
        }

        override fun areContentsTheSame(oldItem: FakeData, newItem: FakeData): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val league = differ.currentList[position]
        setupPlayerRecyclerView(holder)
        holder.leagueName.text = league.league.name
        holder.leagueCountry.text = league.league.country
        playerAdapter.differ.submitList(league.players)
//        playerAdapter.setOnItemClickListener {
//            PlayerInfoBottomSheet(it).show(requireActivity.supportFragmentManager,"")
//        }

    }
    private fun setupPlayerRecyclerView(holder: ViewHolder) {
        playerAdapter = PlayerAdapter(requireActivity)
        holder.playerList.apply {
            adapter = playerAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
