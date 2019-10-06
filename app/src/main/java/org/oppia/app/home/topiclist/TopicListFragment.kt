package org.oppia.app.home.topiclist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.oppia.app.fragment.InjectableFragment
import org.oppia.app.model.TopicSummary
import javax.inject.Inject

/**
 * Fragment that lists all topics available to the user. This fragment must be hosted in an activity that implements
 * [org.oppia.app.home.RouteToTopicListener].
 */
class TopicListFragment : InjectableFragment(), TopicSummaryClickListener {
  @Inject
  lateinit var topicListFragmentPresenter: TopicListFragmentPresenter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    fragmentComponent.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return topicListFragmentPresenter.handleCreateView(inflater, container)
  }

  override fun onTopicSummaryClicked(topicSummary: TopicSummary) {
    topicListFragmentPresenter.onTopicSummaryClicked(topicSummary)
  }
}
