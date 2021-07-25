package com.webworks.practiseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
    }
    fun saveName(view: View){

    }

    fun listRepositories(view: View) {
        val queryRepo = etRepoName.text.toString()
        val  repoLanguage = etLanguage.text.toString()
        val intent = Intent(this, DisplayActivity::class.java)
        intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_REPO)
        intent.putExtra(Constants.KEY_REPO_SEARCH, queryRepo)
        intent.putExtra(Constants.KEY_LANGUAGE,repoLanguage)
        startActivity(intent)

    }

    fun listUserRepositories(view: View) {
        val githubUser = etGithubUser.text.toString()

//        val cls: Class<DisplayActivity> = DisplayActivity::class.java

        val intent = Intent(this, DisplayActivity::class.java)
        intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_USER)
        intent.putExtra(Constants.KEY_GITHUB_USER, githubUser)
        startActivity(intent)

    }
}