package com.example.coolbootsdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coolbootsdemo.R
import com.example.coolbootsdemo.model.Data
import kotlinx.android.synthetic.main.adapter_question_list.view.*

class QuestionListAdapter:PagingDataAdapter<Data,QuestionListAdapter.MainViewHolder>(DataDifferntiator) {

    class MainViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.adapter_question_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.itemView.name.text = getItem(position)?.title
    }

    object DataDifferntiator: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}

