package com.githubissuedemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.githubissuedemo.R
import com.githubissuedemo.databinding.CommentsListItemBinding
import com.githubissuedemo.models.CommentsResponse

class CommentsAdapter(context: Context) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    val mContext = context
    private var mCommentsModel: ArrayList<CommentsResponse>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CommentsViewHolder {
        val mCommentsListItemBinding = DataBindingUtil.inflate<CommentsListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.comments_list_item, viewGroup, false
        )
        return CommentsViewHolder(mCommentsListItemBinding)
    }

    override fun onBindViewHolder(mCommentsViewHolder: CommentsViewHolder, i: Int) {
        if (mCommentsModel != null && mCommentsModel?.size!! > 0) {
            val comment = mCommentsModel!![i]
            mCommentsViewHolder.commentsListItemBinding.mCommentModel = comment
        }
    }

    override fun getItemCount(): Int {
        return if (mCommentsModel != null) {
            mCommentsModel!!.size
        } else {
            0
        }
    }

    fun setCommentList(mCommentModel: ArrayList<CommentsResponse>) {
        this.mCommentsModel = mCommentModel
        notifyDataSetChanged()
    }

    inner class CommentsViewHolder(var commentsListItemBinding: CommentsListItemBinding) :

        RecyclerView.ViewHolder(commentsListItemBinding.root)
}

