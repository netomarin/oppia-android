package org.oppia.app.topic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.oppia.app.activity.InjectableAppCompatActivity
import javax.inject.Inject

const val TOPIC_ACTIVITY_TOPIC_ID_ARGUMENT_KEY = "TopicActivity.topic_id"

/** The activity for tabs in Topic. */
class TopicActivity : InjectableAppCompatActivity() {
  @Inject lateinit var topicActivityPresenter: TopicActivityPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent.inject(this)
    topicActivityPresenter.handleOnCreate()
  }

  companion object {
    /** Returns a new [Intent] to route to [TopicActivity] for a specified topic ID. */
    fun createTopicActivityIntent(context: Context, topicId: String): Intent {
      val intent = Intent(context, TopicActivity::class.java)
      intent.putExtra(TOPIC_ACTIVITY_TOPIC_ID_ARGUMENT_KEY, topicId)
      return intent
    }
  }
}
