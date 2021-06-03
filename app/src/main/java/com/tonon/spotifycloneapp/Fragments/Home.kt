package com.tonon.spotifycloneapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotifyclone.model.SpotifyAPI
import com.app.spotifyclone.model.retrofit
import com.squareup.picasso.Picasso
import com.tonon.spotifycloneapp.Details
import com.tonon.spotifycloneapp.R
import com.tonon.spotifycloneapp.model.*
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.*
import kotlin.collections.ArrayList


class Home : Fragment() {

    private lateinit var  categoryAdapter: CategoryAdapter

    companion object {
        fun newInstance(): Home {
            val fragmentHome = Home()
            val arg = Bundle()
            fragmentHome.arguments = arg
            return fragmentHome
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val categories = arrayListOf<Category>()
        categoryAdapter = CategoryAdapter(categories)
        recyclerView_categories.adapter = categoryAdapter
        recyclerView_categories.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotifyAPI::class.java)
            .listCategories()
            .enqueue(object : Callback<Categories>{
                override fun onFailure(call: Call<Categories>, t: Throwable) {
                    Toast.makeText(context, "erro no servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            categoryAdapter.categories.clear()
                            categoryAdapter.categories.addAll(it.categories)
                            categoryAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })

    }

    private inner class CategoryAdapter( internal val categories: MutableList<Category>): RecyclerView.Adapter<CategoryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            return CategoryHolder(layoutInflater.inflate(R.layout.category_item, parent, false))
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category = categories[position]
            holder.bind(category)
        }

        override fun getItemCount(): Int{
          return categories.size
        }
    }

    private inner class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category){
            itemView.text_title.text = category.title
            itemView.recyclerView_albuns.adapter = AlbunsAdapter(category.albuns){album ->

                val intent = Intent(context, Details::class.java)
                intent.putExtra("album", album.album)
                intent.putExtra("title", titulos[album.id])
                startActivity(intent)
            }
            itemView.recyclerView_albuns.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }


    private inner class AlbunsAdapter(private val albuns: List<Album>, private val listener: ((Album) -> Unit)?): RecyclerView.Adapter<AlbunsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolder = AlbunsHolder(
            layoutInflater.inflate(R.layout.album_item, parent, false), listener)
        

        override fun onBindViewHolder(holder: AlbunsHolder, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }

        override fun getItemCount(): Int = albuns.size
    }

    private inner class AlbunsHolder(itemView: View, val onClick: ((Album) -> Unit)?): RecyclerView.ViewHolder(itemView){
        fun bind(album: Album){
          Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.image_album)
            itemView.image_album.setOnClickListener {
                onClick?.invoke(album)
            }
        }
    }

}