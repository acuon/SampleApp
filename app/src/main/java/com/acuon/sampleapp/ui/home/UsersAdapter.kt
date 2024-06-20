package com.acuon.sampleapp.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acuon.sampleapp.R
import com.acuon.sampleapp.databinding.ItemLayoutUserBinding
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.utils.extensions.executeWithAction
import com.acuon.sampleapp.utils.extensions.inflater
import javax.inject.Inject

class UsersAdapter @Inject constructor() : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var list: List<UserItem?>? = null
        set(value) {
            field = value
            notifyItemRangeChanged(0, value?.size ?: 0)
        }

    private var itemClickListener: ((UserItem?) -> Unit)? = null
    fun onItemClick(callback: (UserItem?) -> Unit) {
        itemClickListener = callback
    }

    private var favoriteClickListener: ((UserItem?, Boolean?) -> Unit)? = null
    fun onFavoriteClick(callback: (UserItem?, Boolean?) -> Unit) {
        favoriteClickListener = callback
    }

    inner class ViewHolder(private val binding: ItemLayoutUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserItem?) {
            binding.executeWithAction { item = user }
            binding.root.setOnClickListener {
                itemClickListener?.invoke(user)
            }
            binding.ivFavorite.setOnClickListener {
                user.run {
                    this?.isFavorite = this?.isFavorite != true
                    favoriteClickListener?.invoke(user, this?.isFavorite)
                    notifyItemChanged(adapterPosition)
                }
            }
            (if (user?.isFavorite == true) R.drawable.ic_favorite_filled else R.drawable.ic_favorite).let {
                binding.ivFavorite.setImageResource(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutUserBinding.inflate(parent.inflater(), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }
}