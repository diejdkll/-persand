package com.grapefruit.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.grapefruit.myapplication.R
import com.grapefruit.myapplication.databinding.ActivityGithubResultBinding
import com.grapefruit.myapplication.model.GithubDTO
import com.grapefruit.myapplication.viewmodel.GithubViewModel

class GithubResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityGithubResultBinding
    lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_github_result)

        viewModel =
            ViewModelProvider(this)[GithubViewModel::class.java]

        val owner = intent.getStringExtra("owner").toString()
        Log.d("owner", "$owner")

        viewModel.getGitInfo(owner)

        val gitObserver = Observer<GithubDTO> {
            val gitInfo = viewModel.gitInfoResponse.value
            Log.d("test5", "$gitInfo")

            with(binding) {
                Glide
                    .with(this@GithubResultActivity)
                    .load(gitInfo?.avatar_url)
                    .centerCrop()
                    .circleCrop()
                    .into(avatarUrl)
                name.text = gitInfo?.name
                login.text = gitInfo?.login
                followers.text = gitInfo?.followers.toString()
                following.text = gitInfo?.following.toString()
                bio.text = gitInfo?.bio


            }
        }

        binding.backBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@GithubResultActivity,
                    GithubSearchActivity::class.java
                )
            )
        }

        viewModel.gitInfoResponse.observe(this, gitObserver)
    }

    override fun onResume() {
        super.onResume()


    }
}