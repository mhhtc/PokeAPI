package com.miguelhh.pokeapi.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.miguelhh.pokeapi.R
import com.miguelhh.pokeapi.utils.EXTRA_EXTERNAL_ID
import com.miguelhh.pokeapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class MainDetail : AppCompatActivity() {

    var pokeID:Int = 0
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        pokeID = intent.getIntExtra(EXTRA_EXTERNAL_ID, 0)

        observeData()
    }

    private fun observeData(){
        viewModel.fetchPokeDetailData(pokeID).observe(this, Observer {
            tv_title.text = it.name.capitalize()
            tv_exp.text = it.baseExperience.toString()

            Glide.with(iv_image)
                .load(it.sprites.other.officialArtwork.frontDefault)
                .centerInside()
                .into(iv_image)

            val listAbility:MutableList<String> = mutableListOf()
            for (value in it.abilities){
                listAbility.add(value.ability.name)
            }

            tv_abilities.text = listAbility.joinToString(", ")

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}