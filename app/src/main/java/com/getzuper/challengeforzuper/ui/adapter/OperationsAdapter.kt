package com.getzuper.challengeforzuper.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.getzuper.challengeforzuper.R
import com.getzuper.challengeforzuper.data.model.MessageFromJS
import com.getzuper.challengeforzuper.databinding.ItemOperationBinding
import kotlinx.android.synthetic.main.item_operation.view.*

class OperationsAdapter: RecyclerView.Adapter<OperationItemViewHolder>() {
    private var curList: ArrayList<MessageFromJS> = ArrayList()
    fun updateList(newList: List<MessageFromJS>) {
        val diffResult = DiffUtil.calculateDiff(MessageDiffCallback(curList, newList))
        curList.clear()
        curList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationItemViewHolder {
        return OperationItemViewHolder(
            ItemOperationBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = curList.size

    override fun onBindViewHolder(holder: OperationItemViewHolder, position: Int) {
        holder.bindData(curList[position])
    }
}

class OperationItemViewHolder(private val binding: ItemOperationBinding): RecyclerView.ViewHolder(binding.root) {
    private lateinit var messageFromJS: MessageFromJS
    fun bindData(message: MessageFromJS) {
        messageFromJS = message
        if (message.isCompletedType() && message.state!! == "error") {
            var oldProgress: String? = "0"
            if (this@OperationItemViewHolder::messageFromJS.isInitialized) {
                oldProgress = messageFromJS.progress
            }
            messageFromJS = MessageFromJS(message.id, message.message, oldProgress, message.state)
        }
        binding.message = messageFromJS
        binding.executePendingBindings()
    }
}

class MessageDiffCallback(val oldList: List<MessageFromJS>, val newList: List<MessageFromJS>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}