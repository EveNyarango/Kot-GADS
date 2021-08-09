package com.webworks.practiseapp

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.webworks.practiseapp.model.Repository
import com.webworks.practiseapp.retrofit.GithubApiService
import com.webworks.practiseapp.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.header.view.*
import retrofit2.Call
import javax.security.auth.callback.Callback

class DisplayActivity : AppCompatActivity() {

    private lateinit var displayAdapter: DisplayAdapter
    private var browsedRepository: List<Repository> = mutableListOf()
    private val githubApiService: GithubApiService by lazy{
        RetrofitClient.githubApiService
    }
//    private var mRealm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Showing Browsed Result"

        setAppUsername()
//        githubApiService.searchRepositoriesByUser()
//        githubApiService.searchRepositories()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = layoutManager

        navigationView.setNavigationItemSelectedListener(this)

        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.drawerLayout, "open", "close")
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val intent = intent
        if(intent.getIntExtra(Constants.KEY_QUERY_TYPE, -1) == Constants.SEARCH_BY_REPO){
            val queryRepo = intent.getStringArrayExtra(Constants.KEY_REPO_SEARCH)
            val repoLanguage = intent.getStringExtra(Constants.KEY_LANGUAGE)
            fetchRepositories(queryRepo, repoLanguage)
        }else{
            val githubUser = intent.getStringExtra(Constants.KEY_GITHUB_USER)
            if (githubUser != null) {
                fetchUserRepositories(githubUser)
            }
        }

    }

    private fun setAppUsername() {
       val sp = getSharedPreferences(Constants.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val personName = sp.getString(Constants.KEY_PERSON_NAME, "User")

        val headerView = navigationView.getHeaderView(0)
        headerView.tvName.text = personName
    }

    private fun fetchUserRepositories(githubUser: String){
       githubApiService.searchRepositoriesByUser(githubUser).enqueue(object :
           Callback<List<Repository>> {

           override fun onResponse(call: Call<List<Repository>>?){
               if(response.isSuccessful){
                   Log.i(TAG, "posts loaded from API" + onResponse()

                       browsedRepositories = response.body()!!
               }

           }

           override fun onFailure(call: Call<List<Repository>>?, t: Throwable?){

           }

       })
    }

    private fun fetchRepositories(queryRepo: Array<String>?, repoLanguage: String?) {

    }

    private fun setupRecyclerView (items: List<Repository>?){
//        displayAdapter = DisplayAdapter(this, items)
//        recyclerView!!.adapter = displayAdapter
    }

    private fun NavigationView.setNavigationItemSelectedListener(displayActivity: DisplayActivity) {

    }

}


