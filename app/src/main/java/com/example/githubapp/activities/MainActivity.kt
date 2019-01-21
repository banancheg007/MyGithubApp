package com.example.githubapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.githubapp.R
import com.example.githubapp.decorator.ItemDecorator
import com.example.githubapp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = mainViewModel.adapter
        recycler_view.addItemDecoration(ItemDecorator(this))

        mainViewModel.userPositionClicked.observe(this, Observer { position ->
            startDetailActivity(position!!)
        })
    }


    private fun startDetailActivity(intentExtra: Int) {
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        intent.putExtra("adapter_position", intentExtra)
        startActivity(intent)
    }

}
