package com.damla.recyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RecyclerViewAdapter(context:Context,rWordList : LinkedList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
   val rWordList:LinkedList<String>
   val rInfulater : LayoutInflater

    init {
        this.rWordList = rWordList
        rInfulater = LayoutInflater.from(context)
    }
    class ViewHolder(itemView: View,rAdapter:RecyclerViewAdapter) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var wordItemView:TextView
        var rAdapter:RecyclerViewAdapter
        init {
            wordItemView = itemView.findViewById(R.id.word);
            this.rAdapter = rAdapter
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var rPosition : Int = adapterPosition
            var element:String = rAdapter.rWordList[rPosition]
            rAdapter.rWordList.set(rPosition,"Clicked! $element")
            rAdapter.notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rItemView :View=rInfulater.inflate(R.layout.recycler_view_content,parent,false)
        return ViewHolder(rItemView,this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rCurrent = rWordList[position]
        holder.wordItemView.setText(rCurrent)
    }

    override fun getItemCount(): Int {
        return rWordList.size
    }
}