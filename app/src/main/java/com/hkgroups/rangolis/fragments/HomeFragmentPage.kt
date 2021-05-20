package com.hkgroups.rangolis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.models.MainModel

class HomeFragmentPage : Fragment() {

   private var list:MutableList<MainModel>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home_page, container, false)
        var recyclerView:RecyclerView = view.findViewById(R.id.homeFragmentRecycler)
        return view
    }


}