package com.tonon.spotifycloneapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.tonon.spotifycloneapp.FragmentsTab.Albuns
import com.tonon.spotifycloneapp.FragmentsTab.Artists
import com.tonon.spotifycloneapp.FragmentsTab.Playlists
import com.tonon.spotifycloneapp.R
import kotlinx.android.synthetic.main.fragment_biblioteca.*


class Biblioteca : Fragment() {

    companion object {
        fun newInstance(): Biblioteca {
            val fragmentBiblioteca = Biblioteca()
            val arg = Bundle()
            fragmentBiblioteca.arguments = arg
            return fragmentBiblioteca
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_biblioteca, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)

            .add("Playlists", Playlists::class.java)
            .add("Artistas", Artists::class.java)
            .add("Albuns", Albuns::class.java)
            .create()
        )

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }


}