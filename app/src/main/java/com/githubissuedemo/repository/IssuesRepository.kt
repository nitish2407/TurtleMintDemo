package com.githubissuedemo.repository

import com.githubissuedemo.models.IssuesResponse
import com.githubissuedemo.network.RetrofitClient
import androidx.lifecycle.MutableLiveData
import com.githubissuedemo.models.CommentsResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IssuesRepository {
    private val mIssueList = ArrayList<IssuesResponse>()
    private val mCommentList = ArrayList<CommentsResponse>()
    private val mutableLiveData = MutableLiveData<List<IssuesResponse>>()
    private val commentsMutableLiveData = MutableLiveData<ArrayList<CommentsResponse>>()

    ////call to internet and  retun  MutableLiveData
    fun getAllGithubIssues(): MutableLiveData<List<IssuesResponse>> {

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service

        ///call the API that define In Interface
        val call = userDataService.apiRequestsIssues

        call.enqueue(object : Callback<JsonArray> {
            internal var message: String? = null

            override fun onResponse(call: Call<JsonArray>, resp: Response<JsonArray>) {

                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                val gson = GsonBuilder().create()

                if (resp != null && resp.body() != null) {

                    val json = JsonParser().parse(resp.body()!!.toString()).asJsonArray
                    //Log.e("data",json.toString())
                    if (json != null) {

                        for (i in 0..json.size() - 1) {
                            try {

                                val jsonobj =
                                    JsonParser().parse(json.get(i).toString()).asJsonObject

                                val responseModel =
                                    gson.fromJson(jsonobj, IssuesResponse::class.java)

                                mIssueList.add(responseModel)

                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }


                        }
                        mutableLiveData.value = mIssueList

                    }
                }


            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                t.printStackTrace()

            }
        })


        return mutableLiveData
    }

    fun getAllComments(commentUrl: String): MutableLiveData<ArrayList<CommentsResponse>> {

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service

        ///call the API that define In Interface
        val call = userDataService.apiRequestsComments(commentUrl)

        call.enqueue(object : Callback<JsonArray> {
            internal var message: String? = null

            override fun onResponse(call: Call<JsonArray>, resp: Response<JsonArray>) {

                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                val gson = GsonBuilder().create()

                if (resp != null && resp.body() != null) {

                    val json = JsonParser().parse(resp.body()!!.toString()).asJsonArray
                    //Log.e("data",json.toString())
                    if (json != null) {

                        for (i in 0..json.size() - 1) {
                            try {

                                val jsonobj =
                                    JsonParser().parse(json.get(i).toString()).asJsonObject

                                val responseModel =
                                    gson.fromJson(jsonobj, CommentsResponse::class.java)

                                mCommentList.add(responseModel)

                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }


                        }
                        commentsMutableLiveData.value = mCommentList

                    }
                }


            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                t.printStackTrace()

            }
        })


        return commentsMutableLiveData
    }

}