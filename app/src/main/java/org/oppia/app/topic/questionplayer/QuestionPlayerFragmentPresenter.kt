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
import org.oppia.app.player.audio.AudioViewModel
import org.oppia.app.viewmodel.ViewModelProvider
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
  private val viewModelProvider: ViewModelProvider<QuestionPlayerViewModel>,
  private val questionTrainingController: QuestionTrainingController,
  private val questionAssessmentProgressController: QuestionAssessmentProgressController
) {

  private val viewModel by lazy {
    getQuestionPlayerViewModel()
  }

  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    startTrainingSession()
    val binding = QuestionPlayerFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    binding.let {
      it.lifecycleOwner = fragment
      it.presenter = this
      it.viewModel = viewModel
    }
    return binding.root
  }

  private fun startTrainingSession() {
    val intent = fragment.requireActivity().intent
    val skillsList = intent.extras?.getStringArrayList(QUESTION_PLAYER_ACTIVITY_SKILL_ID_LIST_ARGUMENT_KEY)
    checkNotNull(skillsList) { "Fragment requires list of skill ids" }
    questionTrainingController.startQuestionTrainingSession(skillsList.toList()).observe(fragment, Observer {startResult ->
      if (startResult.isSuccess()) {
        subscribeToCurrentState()
      }
    })
  }

  private fun subscribeToCurrentState() {
    Transformations.map(questionAssessmentProgressController.getCurrentQuestion(), ::processEphemeralQuestionResult)
      .observe(fragment, Observer {
        
      })
  }

  private fun processEphemeralQuestionResult(result: AsyncResult<EphemeralQuestion>): EphemeralQuestion {
    if (result.isFailure()) {
      logger.e("QuestionPlayerFragmentPresenter", "Failed to ephemeral question")
    }
    return result.getOrDefault(EphemeralQuestion.getDefaultInstance())
  }

  private fun getQuestionPlayerViewModel(): QuestionPlayerViewModel {
    return viewModelProvider.getForFragment(fragment, QuestionPlayerViewModel::class.java)
  }
}
