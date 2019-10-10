package org.oppia.app.topic.questionplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import kotlinx.coroutines.processNextEventInCurrentThread
import org.oppia.app.databinding.QuestionPlayerFragmentBinding
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.EphemeralQuestion
import org.oppia.app.model.Question
import org.oppia.domain.question.QuestionAssessmentProgressController
import org.oppia.domain.question.QuestionTrainingController
import org.oppia.util.data.AsyncResult
import org.oppia.util.logging.Logger
import javax.inject.Inject

/** The presenter for [QuestionPlayerFragment]. */
@FragmentScope
class QuestionPlayerFragmentPresenter @Inject constructor(
  private val fragment: Fragment,
  private val logger: Logger,
  private val questionTrainingController: QuestionTrainingController,
  private val questionAssessmentProgressController: QuestionAssessmentProgressController
) {

  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    startTrainingSession()
    val binding = QuestionPlayerFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    binding.let {
      it.lifecycleOwner = fragment
    }
    return binding.root
  }

  private fun startTrainingSession() {
    val intent = fragment.requireActivity().intent
    checkNotNull(intent.extras) { "Fragment requires list of skill ids" }
    val skillsList = intent.extras!!.getStringArrayList(QUESTION_PLAYER_ACTIVITY_SKILL_ID_LIST_ARGUMENT_KEY)
    Transformations.map(questionTrainingController.getQuestionsForTopic(skillsList), ::processQuestionsForTopicResult)
      .observe(fragment, Observer {questions ->
        questionTrainingController.startQuestionTrainingSession(questions).observe(fragment, Observer {startResult ->
          if (startResult.isSuccess()) {
            //maybe set a boolean to let UI know its interactable
            subscribeToCurrentState()
          }
        })
      })
  }

  private fun subscribeToCurrentState() {
    Transformations.map(questionAssessmentProgressController.getCurrentQuestion(), )
    .observe(fragment, Observer {

    })
  }

  private fun processQuestionsForTopicResult(result: AsyncResult<List<Question>>): List<Question> {
    if (result.isFailure()) {
      logger.e("QuestionPlayerFragmentPresenter", "Failed to get questions for the topic")
    }
    return result.getOrDefault(emptyList())
  }

  private fun processEphemeralQuestionResult(result: AsyncResult<EphemeralQuestion>): EphemeralQuestion {
    if (result.isFailure()) {
      logger.e("QuestionPlayerFragmentPresenter", "Failed to ephemeral question")
    }
    return result.getOrDefault(EphemeralQuestion.getDefaultInstance())
  }
}
