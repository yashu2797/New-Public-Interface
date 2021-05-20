package com.hkgroups.rangolis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.adapters.HomeFragmentAdapter
import com.hkgroups.rangolis.models.MainModel

class HomeFragmentPage : Fragment() {

    private var list: MutableList<MainModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        list = ArrayList()
        val database = Firebase.database
        val reference = database.reference
        val adapter = HomeFragmentAdapter(requireContext(), list!!)
        reference.child("viewType").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    list!!.add(data.getValue(MainModel::class.java)!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        val recyclerView: RecyclerView = view.findViewById(R.id.homeFragmentRecycler)
        recyclerView.adapter = adapter
        return view
    }


}