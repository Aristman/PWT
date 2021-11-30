package ru.marslab.pocketwordtranslator.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.marslab.pocketwordtranslator.databinding.ItemTranslateWordBinding
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi

class TranslationListAdapter : ListAdapter<TranslateWordUi, TranslationViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val binding =
            ItemTranslateWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TranslationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TranslationViewHolder(private val binding: ItemTranslateWordBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TranslateWordUi) {
        binding.originalWord.text = item.word
        binding.translation.text = item.translation
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<TranslateWordUi>() {
    override fun areItemsTheSame(oldItem: TranslateWordUi, newItem: TranslateWordUi): Boolean =
        oldItem.word == newItem.word

    override fun areContentsTheSame(oldItem: TranslateWordUi, newItem: TranslateWordUi): Boolean =
        oldItem == newItem

}