package com.miguelhh.pokeapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelhh.pokeapi.viewmodel.MainViewModel
import com.miguelhh.pokeapi.ui.adapter.PokeAdapter
import com.miguelhh.pokeapi.R
import com.miguelhh.pokeapi.model.PokeItem
import com.miguelhh.pokeapi.ui.adapter.Interaction
import com.miguelhh.pokeapi.utils.EXTRA_EXTERNAL_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Interaction {

    private val adapter: PokeAdapter by lazy { PokeAdapter(this) }
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_pokes.layoutManager = LinearLayoutManager(this)
        rv_pokes.adapter = adapter

        observeData()
    }

    private fun observeData(){
        viewModel.fetchPokeData().observe(this, Observer {
            adapter.setPokeList(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemSelected(position: Int, item: PokeItem) {
        val intent = Intent(this, MainDetail::class.java).apply {
            putExtra(EXTRA_EXTERNAL_ID, item.getId())
        }
        startActivity(intent)
    }

}
