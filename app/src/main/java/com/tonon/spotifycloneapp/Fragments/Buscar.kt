package com.tonon.spotifycloneapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tonon.spotifycloneapp.R
import com.tonon.spotifycloneapp.model.Section
import com.tonon.spotifycloneapp.model.data
import kotlinx.android.synthetic.main.fragment_buscar.*
import kotlinx.android.synthetic.main.section_item.view.*


class Buscar : Fragment() {

    private lateinit var sectionAdapter: SectionAdapter

    companion object {
        fun newInstance(): Buscar {
            val fragmentBuscar = Buscar()
            val arg = Bundle()
            fragmentBuscar.arguments = arg
            return fragmentBuscar
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sectionAdapter = SectionAdapter(data())
        recyclerView_section.adapter = sectionAdapter
        recyclerView_section.layoutManager = GridLayoutManager(context,2)
    }

    private inner class SectionAdapter(private val section: MutableList<Section>): RecyclerView.Adapter<SectionAdapter.SectionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
            return SectionHolder((layoutInflater.inflate(R.layout.section_item, parent, false)))
        }

        override fun onBindViewHolder(holder: SectionHolder, position: Int) {
            val section: Section = section[position]
            holder.bind(section)
        }

        override fun getItemCount(): Int = section.size

        private inner class SectionHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun bind(section: Section){
                itemView.image_section.setImageResource(section.section)
                itemView.name_section.text = section.nameSection
            }
        }
    }


}