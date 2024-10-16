package com.example.dogpick.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpick.data.dogData
import com.example.dogpick.databinding.FavDogCardBinding

class favDogAdapter : RecyclerView.Adapter<favDogAdapter.FavDogViewHolder>(){

    //ここでfav_dog_card.xmlとbindingします
    inner class FavDogViewHolder(val binding:FavDogCardBinding):RecyclerView.ViewHolder(binding.root)

    var onItemClickListener: ((String) -> Unit)? = null

    //新、旧　のものを比較して　重複しないものだけ更新する
    private val diffUnit = object : DiffUtil.ItemCallback<dogData>(){
        override fun areItemsTheSame(oldItem: dogData, newItem: dogData): Boolean {
                return oldItem.message == newItem.message
        }
        override fun areContentsTheSame(oldItem: dogData, newItem: dogData): Boolean {
          return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, diffUnit)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavDogViewHolder {
        return FavDogViewHolder(FavDogCardBinding.inflate(
                LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder: FavDogViewHolder, position: Int) {
       val dog = differ.currentList[position]
        Glide.with(holder.itemView).load(dog.message).into(holder.binding.imgFavDog)
        holder.binding.imgFavDogtext.text = "可愛い！"

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(dog.message)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}