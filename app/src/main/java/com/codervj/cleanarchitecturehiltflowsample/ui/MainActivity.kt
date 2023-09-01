package com.codervj.cleanarchitecturehiltflowsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.codervj.cleanarchitecture_sample.R
import com.codervj.cleanarchitecture_sample.databinding.ActivityMainBinding
import com.codervj.cleanarchitecturehiltflowsample.ui.adapter.RepositoryListViewAdapter
import com.codervj.cleanarchitecturehiltflowsample.ui.state.GetGithubRepoFailure
import com.codervj.cleanarchitecturehiltflowsample.ui.state.GetGithubRepoSuccess
import com.codervj.cleanarchitecturehiltflowsample.ui.state.Loading
import com.codervj.cleanarchitecturehiltflowsample.ui.viewmodel.RepositoryViewModel
import com.codervj.cleanarchitecturehiltflowsample.utils.gone
import com.codervj.cleanarchitecturehiltflowsample.utils.showShortToast
import com.codervj.cleanarchitecturehiltflowsample.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val repositoryViewModel: RepositoryViewModel by viewModels()
    private var viewBinding: ActivityMainBinding? = null
    private var repoListAdapter: RepositoryListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
        collectRepoData()
        getRepoData("virajjage09")
        viewBinding?.ibSearch?.setOnClickListener {
            if (isValidUserName()) {
                Log.d("Git User Name", viewBinding?.etUserName?.text.toString())
                getRepoData(viewBinding?.etUserName?.text.toString())
            } else {
                showShortToast(getString(R.string.msg_valid_user_name))
            }
        }
    }

    private fun isValidUserName() = ((viewBinding?.etUserName?.text?.isNotEmpty() == true
            && (viewBinding?.etUserName?.text?.length ?: 0) > 3))

    private fun getRepoData(userId: String) {
        repositoryViewModel.getRepository(userId)
    }

    private fun collectRepoData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                repositoryViewModel.getUserRepositoryData
                    .collect {
                        when (it) {
                            is GetGithubRepoSuccess -> {
                                viewBinding?.pbRepoLoader?.gone()
                                Log.d("Response Data", it.data.toString())
                                if (it.data.isNotEmpty()) {
                                    repoListAdapter = RepositoryListViewAdapter(it.data)
                                    viewBinding?.recRepoList?.adapter = repoListAdapter
                                    viewBinding?.tvNoErrorMsg?.gone()
                                } else {
                                    viewBinding?.recRepoList?.gone()
                                    viewBinding?.tvNoErrorMsg?.visible()
                                }
                            }

                            is GetGithubRepoFailure -> {
                                viewBinding?.pbRepoLoader?.gone()
                                viewBinding?.tvNoErrorMsg?.visible()
                                showShortToast(it.message)
                            }

                            is Loading -> {
                                viewBinding?.pbRepoLoader?.visible()
                            }
                        }
                    }
            }
        }
    }

}