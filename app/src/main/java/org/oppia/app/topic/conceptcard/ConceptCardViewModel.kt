package org.oppia.app.topic.conceptcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.processNextEventInCurrentThread
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.ConceptCard
import org.oppia.domain.topic.TEST_SKILL_ID_0
import org.oppia.domain.topic.TopicController
import org.oppia.util.data.AsyncResult
import org.oppia.util.logging.Logger
import javax.inject.Inject

/** [ViewModel] for concept card, providing rich text and worked examples */
@FragmentScope
class ConceptCardViewModel @Inject constructor(
  private val topicController: TopicController,
  private val logger: Logger
) : ViewModel() {
  private val skillId: String = TEST_SKILL_ID_0

  fun getConceptCardLiveData(): LiveData<ConceptCard> {
    return Transformations.map(topicController.getConceptCard(skillId), ::processConceptCardResult)
  }

  private fun processConceptCardResult(conceptCardResult: AsyncResult<ConceptCard>): ConceptCard {
    if (conceptCardResult.isFailure()) {
      logger.e("ConceptCardFragment", "Failed to retrieve Concept Card: " + conceptCardResult.getErrorOrNull())
    }
    return conceptCardResult.getOrDefault(ConceptCard.getDefaultInstance())
  }
}