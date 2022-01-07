package com.githubissuedemo.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.githubissuedemo.models.IssuesResponse
import com.githubissuedemo.repository.IssuesRepository

class MainViewModel (application: Application)  : AndroidViewModel(application) {

    private val mIssuesRepository: IssuesRepository

    val getAllIssues: LiveData<List<IssuesResponse>>
        get() = mIssuesRepository.getAllGithubIssues()

    init {
        mIssuesRepository = IssuesRepository()
    }

}