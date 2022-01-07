package com.githubissuedemo

import android.content.Context
import android.view.View
import com.githubissuedemo.models.IssuesResponse

class ClickListeners {
    private var mContext: Context

    constructor(mContext: Context) {
        this.mContext = mContext
    }

    fun onItemClick(view: View?, issuesResponse: IssuesResponse) {

    }
}