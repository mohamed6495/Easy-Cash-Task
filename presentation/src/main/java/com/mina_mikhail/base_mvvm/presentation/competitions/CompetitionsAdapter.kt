package com.mina_mikhail.base_mvvm.presentation.competitions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.databinding.ItemCompetitionBinding

class CompetitionsAdapter(
  private var itemClick: ((item: Competition) -> Unit)
) : ListAdapter<Competition, CompetitionsAdapter.SearchCategoriesViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Competition>() {
      override
      fun areItemsTheSame(oldItem: Competition, newItem: Competition): Boolean =
        oldItem.id == newItem.id

      override
      fun areContentsTheSame(oldItem: Competition, newItem: Competition): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): SearchCategoriesViewHolder {
    val binding: ItemCompetitionBinding =
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_competition,
        parent,
        false
      )

    return SearchCategoriesViewHolder(binding)
  }

  override
  fun onBindViewHolder(holder: SearchCategoriesViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class SearchCategoriesViewHolder(private val itemBinding: ItemCompetitionBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Competition

    init {
      itemBinding.llItem.setOnClickListener {
        itemClick.invoke(currentItem)
      }
    }

    fun bind(item: Competition) {
      currentItem = item
      itemBinding.item = currentItem
    }
  }
}