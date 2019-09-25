package org.oppia.app.topic.conceptcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.ConceptCard
import org.oppia.domain.topic.TEST_SKILL_ID_0
import org.oppia.domain.topic.TopicController
import org.oppia.util.data.AsyncResult
import javax.inject.Inject

/** [ViewModel] for concept card, providing rich text and worked examples */
@FragmentScope
class ConceptCardViewModel @Inject constructor(
  private val topicController: TopicController
  ) : ViewModel() {
  private val skillId: String = TEST_SKILL_ID_0

  fun getConceptCardLiveData(): LiveData<AsyncResult<ConceptCard>> {
    return topicController.getConceptCard(skillId)
  }
}