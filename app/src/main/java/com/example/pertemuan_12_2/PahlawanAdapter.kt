package com.example.pertemuan_12_2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pertemuan_12_2.databinding.EachItemBinding
import com.example.pertemuan_12_2.model.ResultItem

typealias onClickData = (ResultItem) -> Unit

class PahlawanAdapter (private val context: Context, private val listData: List<ResultItem>, private val onClickData: onClickData)
    : RecyclerView.Adapter<PahlawanAdapter.HeroViewHolder>(){

    inner class HeroViewHolder (private val binding: EachItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (data: ResultItem) {
            with(binding){
                namaPahlawan.text = data.title
                itemView.setOnClickListener{
                    onClickData(data)
                }
                Glide.with(context).load(data.image).centerCrop().into(gambarPahlawan)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = EachItemBinding.inflate(
            LayoutInflater.from(
                parent.context), parent, false
        )
        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}