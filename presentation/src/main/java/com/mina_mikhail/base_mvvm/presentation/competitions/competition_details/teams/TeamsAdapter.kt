package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.databinding.ItemTeamBinding

class TeamsAdapter(
  private var itemClick: ((item: Team) -> Unit)
) : ListAdapter<Team, TeamsAdapter.TeamsViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Team>() {
      override
      fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean =
        oldItem.id == newItem.id

      override
      fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TeamsViewHolder {
    val binding: ItemTeamBinding =
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_team,
        parent,
        false
      )

    return TeamsViewHolder(binding)
  }

  override
  fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class TeamsViewHolder(private val itemBinding: ItemTeamBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Team

    init {
      itemBinding.llItem.setOnClickListener {
        itemClick.invoke(currentItem)
      }
    }

    fun bind(item: Team) {
      currentItem = item
      itemBinding.item = currentItem
    }
  }
}