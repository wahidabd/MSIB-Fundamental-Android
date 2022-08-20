package com.wahidabd.githubapps.view.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.githubapps.core.setImage
import com.wahidabd.githubapps.data.model.FavoriteData
import com.wahidabd.githubapps.databinding.ItemUserBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val list = ArrayList<FavoriteData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FavoriteData>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private var listener: ((String) -> Unit)? = null
    fun setOnItemClick(listener: (String) -> Unit){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: FavoriteData, listener: ((String) -> Unit)?){
            with(binding){
                tvUsername.text = data.login
                imgAvatar.setImage(data.avatar.toString())

                btnDetail.setOnClickListener {
                    listener?.let { listener(data.login) }
                }
            }
        }
    }
}