package com.webworks.practiseapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

companion object{
    private val TAG: String = MainActivity::class.java.simpleName
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
    }
    fun saveName(view: View) {
        if (isNotEmpty(etName, inputLayoutName)) {
            val personName = etName.text.toString()
            val sp = getSharedPreferences(Constants.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString(Constants.KEY_PERSON_NAME, personName)
            editor.apply()

        }
    }

    fun listRepositories(view: View) {
        if(isNotEmpty(etRepoName, inputLayoutRepoName)) {
            val queryRepo = etRepoName.text.toString()
            val repoLanguage = etLanguage.text.toString()
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_REPO)
            intent.putExtra(Constants.KEY_REPO_SEARCH, queryRepo)
            intent.putExtra(Constants.KEY_LANGUAGE, repoLanguage)
            startActivity(intent)
        }
    }

    fun listUserRepositories(view: View) {
        if(isNotEmpty(etGithubUser, inputLayoutGithubUser)) {
            val githubUser = etGithubUser.text.toString()

//        val cls: Class<DisplayActivity> = DisplayActivity::class.java

            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_USER)
            intent.putExtra(Constants.KEY_GITHUB_USER, githubUser)
            startActivity(intent)

        }

    }

    fun isNotEmpty(editText: EditText, textInputLayout: TextInputLayout) : Boolean{
        if (editText.text.toString().isEmpty()){
            textInputLayout.error = "Cannot be blank !"
            return false
        }else{
            textInputLayout.isErrorEnabled = false
            return true
        }
    }
}