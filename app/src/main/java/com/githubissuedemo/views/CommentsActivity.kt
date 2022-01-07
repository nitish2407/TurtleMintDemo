package com.githubissuedemo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubissuedemo.R
import com.githubissuedemo.adapters.CommentsAdapter
import com.githubissuedemo.databinding.ActivityCommentsBinding
import com.githubissuedemo.models.CommentsResponse
import com.githubissuedemo.models.IssuesResponse
import com.githubissuedemo.viewModels.CommentsViewModel
import com.google.gson.Gson

class CommentsActivity : AppCompatActivity() {
    internal var loadBar: ProgressBar? = null
    private lateinit var activityCommentsBinding: ActivityCommentsBinding
    private var commentsViewModel: CommentsViewModel? = null
    private var mCommentsAdapter: CommentsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCommentsBinding =DataBindingUtil.setContentView(this,R.layout.activity_comments)
        // bind RecyclerView
        val recyclerView = activityCommentsBinding.rvComments
        loadBar = activityCommentsBinding.loadBar
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        commentsViewModel = ViewModelProviders.of(this).get(CommentsViewModel::class.java)

        mCommentsAdapter = CommentsAdapter(this)
        recyclerView.adapter = mCommentsAdapter

        val issue = intent.getStringExtra("issue")
        val issueResponse: IssuesResponse = Gson().fromJson(issue, IssuesResponse::class.java)

        commentsViewModel!!.getAllComments(issueResponse.commentsUrl)
        initObserver()
    }

    //https://github.com/nitish2407/TurtleMintDemo.git

    private fun initObserver() {
        commentsViewModel?.allComments?.observe(this,
            Observer<List<CommentsResponse>> { mCommentsModel ->
                ///if any thing chnage the update the UI
                mCommentsAdapter?.setCommentList(mCommentsModel as ArrayList<CommentsResponse>)
                loadBar?.visibility = View.GONE
            })
    }
}