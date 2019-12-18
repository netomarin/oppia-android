package org.oppia.app.onboarding

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withAlpha
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.oppia.app.R
import org.oppia.app.profile.ProfileActivity

/** Tests for [OnboardingFragment]. */
@RunWith(AndroidJUnit4::class)
class OnboardingFragmentTest {

  @Before
  fun setUp() {
    Intents.init()
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlideTitle_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_0_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlideDescription_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(
        allOf(
          withId(R.id.slide_description_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_0_description)))
    }
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlideImage_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(
        allOf(
          withId(R.id.slide_image_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withTagValue(equalTo(R.drawable.ic_onboarding_0))))
    }
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlide_index0DotIsActive_otherDotsAreInactive() {
    launch(OnboardingActivity::class.java).use {
      onView(
        allOf(
          withId(R.id.onboarding_dot_0),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(1.0F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_1),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_2),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_3),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
    }
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlide_skipButtonIsVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.skip_text_view)).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlide_clickSkipButton_shiftsToLastSlide() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.skip_text_view)).perform(click())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_3_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkDefaultSlide_getStartedButtonIsNotVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.get_started_button)).check(matches(not(isDisplayed())))
    }
  }

  @Test
  fun testOnboardingFragment_swipeRight_isNotWorking() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeRight())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_image_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withTagValue(equalTo(R.drawable.ic_onboarding_0))))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1Title_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_1_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1Description_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_description_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_1_description)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1Image_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_image_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withTagValue(equalTo(R.drawable.ic_onboarding_1))))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1_index1DotIsActive_otherDotsAreInactive() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.onboarding_dot_0),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_1),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(1.0F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_2),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_3),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1_skipButtonIsVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.skip_text_view)).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1_clickSkipButton_shiftsToLastSlide() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.skip_text_view)).perform(click())
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_3_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide1_getStartedButtonIsNotVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.get_started_button)).check(matches(not(isDisplayed())))
    }
  }

  @Test
  fun testOnboardingFragment_swipeLeftThenSwipeRight_isWorking() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeRight())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_image_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withTagValue(equalTo(R.drawable.ic_onboarding_0))))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2Title_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_2_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2Description_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_description_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_2_description)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2Image_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_image_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withTagValue(equalTo(R.drawable.ic_onboarding_2))))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2_index2DotIsActive_otherDotsAreInactive() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.onboarding_dot_0),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_1),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_2),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(1.0F)))
      onView(
        allOf(
          withId(R.id.onboarding_dot_3),
          isCompletelyDisplayed()
        )
      ).check(matches(withAlpha(0.3F)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2_skipButtonIsVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.skip_text_view)).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2_clickSkipButton_shiftsToLastSlide() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.skip_text_view)).perform(click())
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_3_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide2_getStartedButtonIsNotVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.get_started_button)).check(matches(not(isDisplayed())))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide3Title_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_3_title)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide3Description_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_description_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_3_description)))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide3Image_isCorrect() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_image_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withTagValue(equalTo(R.drawable.ic_onboarding_3))))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide3_skipButtonIsNotVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.skip_text_view)).check(matches(not(isDisplayed())))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide3_getStartedButtonIsVisible() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.get_started_button)).check(matches(isDisplayed()))
    }
  }

  @Test
  fun testOnboardingFragment_checkSlide3_clickGetStartedButton_opensProfileActivity() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(withId(R.id.get_started_button)).perform(click())
      intended(hasComponent(ProfileActivity::class.java.name))
    }
  }

  @Test
  fun testOnboardingFragment_swipeLeftOnLastSlide_isNotWorking() {
    launch(OnboardingActivity::class.java).use {
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      onView(withId(R.id.onboarding_slide_view_pager)).perform(swipeLeft())
      SystemClock.sleep(500)
      onView(
        allOf(
          withId(R.id.slide_title_text_view),
          isCompletelyDisplayed()
        )
      ).check(matches(withText(R.string.slide_3_title)))
    }
  }

  @After
  fun tearDown() {
    Intents.release()
  }
}
