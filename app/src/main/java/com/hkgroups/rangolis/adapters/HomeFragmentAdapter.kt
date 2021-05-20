package com.hkgroups.rangolis.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.models.MainModel
import com.hkgroups.rangolis.models.ViewpagerModel

class HomeFragmentAdapter(var context: Context,var list:List<MainModel>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val VIEW_PAGER_TYPE = 1
    }

    inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun setData(itemModel:MainModel?){
            val text:TextView = itemView.findViewById(R.id.viewPageTextChange)
            text.text = itemModel!!.title
            val data :MutableList<ViewpagerModel>? = ArrayList()
            val viewPager = itemView.findViewById<ViewPager2>(R.id.mainViewPager)
            val adapter = ViewPagerAdapter(context,data!!)
            val database = Firebase.database
            val reference = database.reference
            reference.child(itemModel.title).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataList in snapshot.children){
                        data.add(0,dataList.getValue(ViewpagerModel::class.java)!!)
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
            viewPager.adapter  = adapter
        }
    }

    override fun getItemViewType(position: Int): Int {
       return when(list[position].type){
            1 -> VIEW_PAGER_TYPE
            else -> VIEW_PAGER_TYPE
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       when(viewType){
           VIEW_PAGER_TYPE ->{
               val view = LayoutInflater.from(context).inflate(R.layout.home_recycler_viewpager_model,parent,false)
               return ViewPagerViewHolder(view)
           }
           else ->{
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