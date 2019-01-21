package com.example.githubapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.githubapp.R
import com.example.githubapp.viewmodels.UserDetailsViewModel
import com.example.githubapp.models.GitHubUserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {

    lateinit var userDetailsViewModel: UserDetailsViewModel

    companion object {
        lateinit var userPageUrl: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        userDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)

        getIntents()

        user_login.setOnClickListener {
            openPageInWeb(userPageUrl)
        }

        userDetailsViewModel.dataResponse.observe(this, Observer {
            showUserInfo(it!!)
        })
    }

    private fun getIntents() {
        val position: Int = intent.getIntExtra("POSITION", 0)
        if (intent.data != null) {
            val data: String = intent.data.path.drop(1)
            Log.e("data", data)
            userDetailsViewModel.makeRequest(data)
        }else {
            Log.e("getIntents()", "intent $position")
            userDetailsViewModel.makeRequest(position)
        }
    }

    private fun openPageInWeb(url: String) {
        val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        openLinkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(openLinkIntent)
    }

    private fun showUserInfo(userResponse: GitHubUserResponse) {
        Log.e("getUserDetails", "user ${userResponse.login}")
        user_name.text = userResponse.name
        user_login.text = "${userResponse.login}"
        user_repository.text = userResponse.repository.toString()
        user_followers.text = userResponse.followers.toString()
        user_following.text = userResponse.following.toString()
        userPageUrl = userResponse.pageUrl

        Picasso.get().load(userResponse.avatar).into(user_photo_url)
    }
}
