package com.example.githubapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.githubapp.R
import com.example.githubapp.viewmodels.ProfileViewModel
import com.example.githubapp.models.GitHubUserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    lateinit var profileViewModel: ProfileViewModel

    companion object {
        lateinit var userPageUrl: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        getIntents()

        user_login.setOnClickListener {
            openLink(userPageUrl)
        }

        profileViewModel.githubResponse.observe(this, Observer {
            showDetails(it!!)
        })
    }

    private fun getIntents() {
        val position: Int = intent.getIntExtra("adapter_position", 55)
        if (intent.data != null) {
            val data: String = intent.data.path.drop(1)
            profileViewModel.makeRequest(data)
        }else {
            profileViewModel.makeRequest(position)
        }
    }

    private fun openLink(url: String) {
        val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        openLinkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(openLinkIntent)
    }

    private fun showDetails(userResponse: GitHubUserResponse) {
        user_name.text = userResponse.name
        user_id.text = userResponse.id.toString()
        user_gists.text = userResponse.gists.toString()
        user_url.text = userResponse.url
        user_login.text = "${userResponse.login}"
        user_repository.text = userResponse.repositories.toString()
        user_followers.text = userResponse.followers.toString()
        user_following.text = userResponse.following.toString()
        userPageUrl = userResponse.url

        Picasso.get().load(userResponse.avatar).into(user_photo_url)
    }
}
