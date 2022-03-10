package com.mina_mikhail.base_mvvm.presentation.tutorial

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import codes.mina_mikhail.app_tutorial.AppTutorial
import codes.mina_mikhail.app_tutorial.AppTutorialHelper
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.auth.AuthActivity
import com.mina_mikhail.base_mvvm.presentation.base.BaseActivity
import com.mina_mikhail.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.mina_mikhail.base_mvvm.presentation.databinding.ActivityTutorialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialActivity : BaseActivity<ActivityTutorialBinding>() {

  private val viewModel: TutorialViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.activity_tutorial

  override
  fun setUpViews() {
    setUpAppTutorial()

    binding.btnSkip.setOnClickListener {
      viewModel.setFirstTime(false)
      openLogIn()
    }
  }

  private fun setUpAppTutorial() {
    val tutorialData = ArrayList<AppTutorial>()
    tutorialData.apply {
      tutorialData.add(
        AppTutorial(
          resources.getString(R.string.tutorial_1_title),
          resources.getString(R.string.tutorial_1_hint),
          ContextCompat.getDrawable(baseContext, R.drawable.ic_intro_1)!!
        )
      )

      tutorialData.add(
        AppTutorial(
          resources.getString(R.string.tutorial_2_title),
          resources.getString(R.string.tutorial_2_hint),
          ContextCompat.getDrawable(baseContext, R.drawable.ic_intro_2)!!
        )
      )

      tutorialData.add(
        AppTutorial(
          resources.getString(R.string.tutorial_3_title),
          resources.getString(R.string.tutorial_3_hint),
          ContextCompat.getDrawable(baseContext, R.drawable.ic_intro_3)!!
        )
      )
    }

    AppTutorialHelper.Builder(this, lifecycle)
      .setTutorialData(tutorialData)
      .setTitleColor(R.color.colorPrimaryDark)
      .setContentColor(R.color.gray)
      .setSliderContainerResourceID(R.id.tutorial_container)
      .setActiveIndicatorColor(R.color.colorAccent)
      .setInActiveIndicatorColor(R.color.gray)
      .setAutoScrolling(false)
      .setNextButtonTextColor(R.color.white)
      .setNextButtonBackground(R.drawable.btn_accent)
      .setSkipTutorialClick {
        viewModel.setFirstTime(false)
        openLogIn()
      }
      .create()
  }

  private fun openLogIn() {
    openActivityAndClearStack(AuthActivity::class.java)
  }
}