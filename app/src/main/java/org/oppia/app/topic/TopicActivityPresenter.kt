package org.oppia.app.topic

import androidx.appcompat.app.AppCompatActivity
import org.oppia.app.R
import org.oppia.app.activity.ActivityScope
import org.oppia.util.logging.Logger
import javax.inject.Inject

/** The presenter for [TopicActivity]. */
@ActivityScope
class TopicActivityPresenter @Inject constructor(
  private val activity: AppCompatActivity,
  private val logger: Logger
) {
  fun handleOnCreate() {
    activity.setContentView(R.layout.topic_activity)
    if (getTopicFragment() == null) {
      activity.supportFragmentManager.beginTransaction().add(
        R.id.topic_fragment_placeholder,
        TopicFragment()
      ).commitNow()
    }

    val topicId = activity.intent.getStringExtra(TOPIC_ACTIVITY_TOPIC_ID_ARGUMENT_KEY)
    logger.i("Topic", "Loading topic fragment for topic ID: $topicId")
  }

  private fun getTopicFragment(): TopicFragment? {
    return activity.supportFragmentManager.findFragmentById(R.id.topic_fragment_placeholder) as TopicFragment?
  }
}
