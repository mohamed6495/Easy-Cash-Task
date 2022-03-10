package com.mina_mikhail.base_mvvm.presentation.auth.log_in

import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import androidx.fragment.app.viewModels
import com.mina_mikhail.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyDrawable
import com.mina_mikhail.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.mina_mikhail.base_mvvm.presentation.base.extensions.showSnackBar
import com.mina_mikhail.base_mvvm.presentation.base.utils.showSoftInput
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
    viewModel.togglePassword.observe(this) {
      if (binding.etPassword.transformationMethod.javaClass.simpleName.equals(
          "PasswordTransformationMethod"
        )
      ) {
        binding.etPassword.transformationMethod = SingleLineTransformationMethod()
        binding.ivPasswordToggle.setImageDrawable(getMyDrawable(R.drawable.ic_hide_password))
      } else {
        binding.etPassword.transformationMethod = PasswordTransformationMethod()
        binding.ivPasswordToggle.setImageDrawable(getMyDrawable(R.drawable.ic_show_password))
      }
      binding.etPassword.setSelection(binding.etPassword.text.length)
    }

    viewModel.validationException.observe(this) {
      when (it) {
        AuthFieldsValidation.EMPTY_EMAIL.value -> {
          binding.etEmail.requestFocus()
          showSoftInput(binding.etEmail, requireContext())
          requireView().showSnackBar(resources.getString(R.string.empty_email))
        }
        AuthFieldsValidation.INVALID_EMAIL.value -> {
          binding.etEmail.requestFocus()
          showSoftInput(binding.etEmail, requireContext())
          requireView().showSnackBar(resources.getString(R.string.invalid_email))
        }
        AuthFieldsValidation.EMPTY_PASSWORD.value -> {
          binding.etPassword.requestFocus()
          showSoftInput(binding.etPassword, requireContext())
          requireView().showSnackBar(resources.getString(R.string.empty_password))
        }
      }
    }

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