package com.grapefruit.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.grapefruit.myapplication.R
import com.grapefruit.myapplication.databinding.ActivityGithubSearchBinding
import com.grapefruit.myapplication.viewmodel.GithubViewModel

class GithubSearchActivity : AppCompatActivity() {

    lateinit var binding: ActivityGithubSearchBinding
    lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_github_search)

        viewModel =
            ViewModelProvider(this)[GithubViewModel::class.java]

        with(binding) {
            var text: String = ""
            gitEdittext.addTextChangedListener {
                text = gitEdittext.text.toString()
            }

            gitSearchBtn.setOnClickListener {
                val intent = Intent(this@GithubSearchActivity, GithubResultActivity::class.java)
                intent.putExtra("owner", text)
                startActivity(intent)

            }
        }

    }
}