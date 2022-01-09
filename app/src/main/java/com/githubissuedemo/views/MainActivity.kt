package com.githubissuedemo.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubissuedemo.R
import com.githubissuedemo.adapters.MainAdapter
import com.githubissuedemo.databinding.ActivityMainBinding
import com.githubissuedemo.db.DatabaseHandler
import com.githubissuedemo.models.CommentsResponse
import com.githubissuedemo.models.IssuesResponse
import com.githubissuedemo.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var activityMainBinding: ActivityMainBinding

    internal var loadBar : ProgressBar? = null
    var mainViewModel: MainViewModel? = null
    private var mIssueAdapter:MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        val recyclerView = activityMainBinding.rvIssues
        loadBar = activityMainBinding.loadBar
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        ///init the View Model
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //init the Custom adataper
        mIssueAdapter = MainAdapter(this)
        //set the CustomAdapter
        recyclerView.adapter = mIssueAdapter

        mainViewModel!!.getAllIssues()
        initObserver()
    }

    private fun initObserver() {

        mainViewModel?.allIssues?.observe(this,
            Observer<ArrayList<IssuesResponse>> { mIssuesModel ->
                ///if any thing chnage the update the UI
                val db = DatabaseHandler(this, null)
                val issueList = mIssuesModel as ArrayList<IssuesResponse>

                db.addIssue(issueList)
                mIssueAdapter?.setIssuesList(mIssuesModel as ArrayList<IssuesResponse>)
                loadBar?.visibility = View.GONE
            })
    }
}