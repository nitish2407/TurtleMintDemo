package com.githubissuedemo.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.githubissuedemo.models.CommentsResponse
import com.githubissuedemo.repository.IssuesRepository

class CommentsViewModel (application: Application)  : AndroidViewModel(application) {

    private val mIssuesRepository: IssuesRepository = IssuesRepository()
    var allComments: MutableLiveData<ArrayList<CommentsResponse>>? = null

    init {
        allComments = MutableLiveData<ArrayList<CommentsResponse>>()
    }

        fun getAllComments(commentUrl :String?) {
        allComments = commentUrl?.let { mIssuesRepository.getAllComments(it) }!!
    }
}