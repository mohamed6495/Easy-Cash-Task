package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.team_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Player
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.databinding.ItemPlayerBinding

class PlayersAdapter : ListAdapter<Player, PlayersAdapter.PlayersViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Player>() {
      override
      fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean =
        oldItem.id == newItem.id

      override
      fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): PlayersViewHolder {
    val binding: ItemPlayerBinding =
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_player,
        parent,
        false
      )

    return PlayersViewHolder(binding)
  }

  override
  fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class PlayersViewHolder(private val itemBinding: ItemPlayerBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Player

    fun bind(item: Player) {
      currentItem = item
      itemBinding.item = currentItem
    }
  }
}