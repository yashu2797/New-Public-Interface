package com.hkgroups.rangolis.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.models.MainModel

class HomeFragmentAdapter(var context: Context,var list:List<MainModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val VIEW_PAGER_TYPE = 1
    }

    inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun setData(itemModel:MainModel){
            var viewPager = itemView.findViewById<ViewPager2>(R.id.mainViewPager)
            
        }
    }

    override fun getItemViewType(position: Int): Int {
       return when(list[position].type){
            1 -> VIEW_PAGER_TYPE
            else -> VIEW_PAGER_TYPE
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       when(getItemViewType(viewType)){
           VIEW_PAGER_TYPE ->{
               val view = LayoutInflater.from(context).inflate(R.layout.home_recycler_viewpager_model,parent,false)
               return ViewPagerViewHolder(view)
           }
       }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (getItemViewType(position)== VIEW_PAGER_TYPE){
           holder as ViewPagerViewHolder
           val list  = list[position]
           holder.setData(list)
       }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}