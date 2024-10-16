package com.example.dogpick.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpick.data.dogDataS
import com.example.dogpick.databinding.PicMaybeWillLikeBinding

class mayBelikeAdapter(): RecyclerView.Adapter<mayBelikeAdapter.MaybelikeViewHolder>(){

    //pic_maybe_will_likeとbinding
    class MaybelikeViewHolder(val binding:PicMaybeWillLikeBinding):RecyclerView.ViewHolder(binding.root){}

    private var dogDataSList : dogDataS = dogDataS(emptyList(),"")
    var onItemClickListener: ((String) -> Unit)? = null

    fun setDogDataSList(dogDataSList: dogDataS){
        this.dogDataSList = dogDataSList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaybelikeViewHolder {
      return MaybelikeViewHolder(PicMaybeWillLikeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: MaybelikeViewHolder, position: Int) {
        Glide.with(holder.itemView).load(dogDataSList.message[position]).into(holder.binding.imgMaybeWillLike)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(dogDataSList.message[position])
        }


    }

    override fun getItemCount():Int {
       return dogDataSList.message.size
    }


}

