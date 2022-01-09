package com.githubissuedemo.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.githubissuedemo.Utils
import com.githubissuedemo.db.DatabaseHandler
import com.githubissuedemo.models.IssuesResponse
import com.githubissuedemo.repository.IssuesRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mIssuesRepository: IssuesRepository = IssuesRepository()
    var allIssues: MutableLiveData<ArrayList<IssuesResponse>>? = null

    fun getAllIssues() {
        if (Utils.isNetworkConnected(getApplication()))
            allIssues = mIssuesRepository.getAllGithubIssues()
        else {
            val db = DatabaseHandler(getApplication(), null)
            allIssues = db.getAllIssuesFromDb()
        }
    }

}