package com.example.githubapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.githubapp.adapter.UserAdapter

class MainViewModel(
    application: Application
) : AndroidViewModel(application),
    UserAdapter.OnItemClick {

    val adapter: UserAdapter by lazy { UserAdapter(this) }

    private val _userPositionClicked = MutableLiveData<Int>()
    val userPositionClicked: LiveData<Int> = _userPositionClicked

    override fun onUserClicked(position: Int) {
        _userPositionClicked.value = position
    }

}