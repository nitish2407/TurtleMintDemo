package com.githubissuedemo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.githubissuedemo.ClickListeners
import com.githubissuedemo.R
import com.githubissuedemo.databinding.RowListItemBinding
import com.githubissuedemo.models.IssuesResponse
import com.githubissuedemo.views.CommentsActivity
import com.githubissuedemo.views.MainActivity
import com.google.gson.Gson

class MainAdapter(context: Context) : RecyclerView.Adapter<MainAdapter.IssuesViewHolder>() {
    val mContext = context
    private var mIssueModel: ArrayList<IssuesResponse>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): IssuesViewHolder {
        val mIssueListItemBinding = DataBindingUtil.inflate<RowListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.row_list_item, viewGroup, false
        )
        return IssuesViewHolder(mIssueListItemBinding)
    }

    override fun onBindViewHolder(mIssueViewHolder: IssuesViewHolder, i: Int) {
        val currentIssue = mIssueModel!![i]
        mIssueViewHolder.mIssueListItemBinding.mIssueModel = currentIssue
        mIssueViewHolder.itemView.setOnClickListener {
            if (mIssueViewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                if (currentIssue.comments!! > 0) {
                    val intent = Intent(mContext, CommentsActivity::class.java)
                    intent.putExtra("issue", Gson().toJson(currentIssue))
                    mContext.startActivity(intent)
                } else
                    Toast.makeText(mContext.applicationContext,"No Comments Available",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mIssueModel != null) {
            mIssueModel!!.size
        } else {
            0
        }
    }

    fun setIssuesList(mIssueModel: ArrayList<IssuesResponse>) {
        this.mIssueModel = mIssueModel
        notifyDataSetChanged()
    }

    inner class IssuesViewHolder(var mIssueListItemBinding: RowListItemBinding) :

        RecyclerView.ViewHolder(mIssueListItemBinding.root)
}

