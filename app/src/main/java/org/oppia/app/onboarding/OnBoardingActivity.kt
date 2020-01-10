package org.oppia.app.onboarding

import android.os.Bundle
import org.oppia.app.activity.InjectableAppCompatActivity
import org.oppia.app.profile.ProfileActivity
import javax.inject.Inject

/** An activity that shows a temporary on-boarding screen until the app is on-boarded then navigates to [ProfileActivity]. */
class OnBoardingActivity : InjectableAppCompatActivity() {

  @Inject lateinit var onBoardingActivityPresenter: OnBoardingActivityPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent.inject(this)
    onBoardingActivityPresenter.handleOnCreate()
  }
}
