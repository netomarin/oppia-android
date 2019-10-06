package org.oppia.app.home.topiclist

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.PromotedStory
import org.oppia.app.model.TopicList
import org.oppia.app.model.TopicSummary
import org.oppia.domain.topic.TopicListController
import org.oppia.util.data.AsyncResult
import javax.inject.Inject

/**
 * [ViewModel] for showing a list of topic summaries. Note that this can only be hosted in fragments that implement
 * [TopicSummaryClickListener].
 */
@FragmentScope
class TopicListViewModel @Inject constructor(
  fragment: Fragment, private val topicListController: TopicListController
) : ViewModel() {
  private val topicSummaryClickListener = fragment as TopicSummaryClickListener
  /**
   * The retrieved [LiveData] for retrieving topic summaries. This model should ensure only one
   * [LiveData] is used for all subsequent processed data to ensure the transformed [LiveData]s are
   * always in sync.
   */
  private val topicListSummaryResultLiveData: LiveData<AsyncResult<TopicList>> by lazy {
    topicListController.getTopicList()
  }
  val topicListSummaryLiveData: LiveData<List<TopicSummaryViewModel>> by lazy { getTopicList() }
  val topicListLookupSucceeded: LiveData<Boolean> by lazy { getWhetherTopicListLookupSucceeded() }
  val topicListLookupFailed: LiveData<Boolean> by lazy { getWhetherTopicListLookupFailed() }
  val topicListIsLoading: LiveData<Boolean> by lazy { getWhetherTopicListIsLoading() }

  private fun getTopicList(): LiveData<List<TopicSummaryViewModel>> {
    return Transformations.map(getAssumedSuccessfulTopicList()) {
      expandList(it.topicSummaryList).map(this::createTopicSummaryViewModel)
    }
  }

  private fun getAssumedSuccessfulTopicList(): LiveData<TopicList> {
    // If there's an error loading the data, assume the default.
    return Transformations.map(topicListSummaryResultLiveData) { it.getOrDefault(TopicList.getDefaultInstance()) }
  }

  private fun getWhetherTopicListLookupSucceeded(): LiveData<Boolean> {
    return Transformations.map(topicListSummaryResultLiveData) { it.isSuccess() }
  }

  private fun getWhetherTopicListLookupFailed(): LiveData<Boolean> {
    return Transformations.map(topicListSummaryResultLiveData) { it.isFailure() }
  }

  private fun getWhetherTopicListIsLoading(): LiveData<Boolean> {
    return Transformations.map(topicListSummaryResultLiveData) { it.isPending() }
  }

  private fun createTopicSummaryViewModel(topicSummary: TopicSummary): TopicSummaryViewModel {
    return TopicSummaryViewModel(topicSummary, topicSummaryClickListener)
  }

  private fun <T> expandList(list: List<T>): List<T> {
    val vals = mutableListOf<T>()
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    vals += list
    return vals
  }
}
