package com.mina_mikhail.base_mvvm.presentation.auth.log_in

import androidx.fragment.app.viewModels
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentLogInBinding
import com.mina_mikhail.base_mvvm.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>() {

  private val viewModel: LogInViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_log_in

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
//    lifecycleScope.launchWhenResumed {
//      viewModel.logInResponse.collect {
//        when (it) {
//          Resource.Loading -> {
//            hideKeyboard()
//            showLoading()
//          }
//          is Resource.Success -> {
//            hideLoading()
//            openHome()
//          }
//          is Resource.Failure -> {
//            hideLoading()
//            handleApiError(it, retryAction = { viewModel.onLogInClicked() })
//          }
//        }
//      }
//    }
  }

  private fun openHome() {
    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }
}