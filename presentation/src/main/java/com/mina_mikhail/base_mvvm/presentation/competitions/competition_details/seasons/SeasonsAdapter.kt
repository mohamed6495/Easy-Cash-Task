package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.seasons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Season
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.databinding.ItemSeasonBinding

class SeasonsAdapter : ListAdapter<Season, SeasonsAdapter.SeasonsViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Season>() {
      override
      fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean =
        oldItem.id == newItem.id

      override
      fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): SeasonsViewHolder {
    val binding: ItemSeasonBinding =
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_season,
        parent,
        false
      )

    return SeasonsViewHolder(binding)
  }

  override
  fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class SeasonsViewHolder(private val itemBinding: ItemSeasonBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Season

    fun bind(item: Season) {
      currentItem = item
      itemBinding.item = currentItem
    }
  }
}