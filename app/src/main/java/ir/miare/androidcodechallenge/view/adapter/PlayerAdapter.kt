package ir.miare.androidcodechallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.model.model.Player
import ir.miare.androidcodechallenge.view.PlayerInfoBottomSheet

class PlayerAdapter(val requireActivity: FragmentActivity) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    private var onItemClickListener: ((Player) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerName: TextView = view.findViewById(R.id.player_name)
        val teamName: TextView = view.findViewById(R.id.team_name)
        val teamRank: TextView = view.findViewById(R.id.rank)
    }
    private val differCallback = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_player,
            parent,
            false,
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = differ.currentList[position]
        holder.playerName.text = player.name
        holder.teamName.text = player.team.name
        holder.teamRank.text = player.team.rank.toString()

        holder.itemView.setOnClickListener {
            PlayerInfoBottomSheet(player).show(requireActivity.supportFragmentManager, "")
        }
    }
    fun setOnItemClickListener(listener: (Player) -> Unit) {
        onItemClickListener = listener
    }
}
