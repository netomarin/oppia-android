package org.oppia.app.topic.questionplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import kotlinx.coroutines.processNextEventInCurrentThread
import org.oppia.app.databinding.QuestionPlayerFragmentBinding
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.EphemeralQuestion
import org.oppia.app.model.EphemeralState
import org.oppia.app.model.Question
import org.oppia.app.player.audio.AudioViewModel
import org.oppia.app.viewmodel.ViewModelProvider
import org.oppia.domain.question.QuestionAssessmentProgressController
import org.oppia.domain.question.QuestionTrainingController
import org.oppia.util.data.AsyncResult
import org.oppia.util.logging.Logger
import javax.inject.Inject

private const val MULTIPLE_CHOICE_INPUT = "MultipleChoiceInput"
private const val ITEM_SELECT_INPUT = "ItemSelectionInput"
private const val TEXT_INPUT = "TextInput"
private const val FRACTION_INPUT = "FractionInput"
private const val NUMERIC_INPUT = "NumericInput"
private const val NUMERIC_WITH_UNITS = "NumberWithUnits"

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

  val currentEphemeralQuestion = ObservableField<EphemeralQuestion>()
  val numCorrectQuestions = 0

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
        currentEphemeralQuestion.set(it)
        val currentQuestion = it.currentQuestionIndex
        val totalQuestions = it.totalQuestionCount
        updateInteractionInputByState(it.ephemeralState)
        updateProgressBar(currentQuestion, totalQuestions)
        //set question adapter information
      })
  }

  private fun updateInteractionInputByState(ephemeralState: EphemeralState) {
    //TODO create the input fragment and append onto interaction_container
    when (ephemeralState.state.interaction.id) {
      ITEM_SELECT_INPUT -> {

      }
      MULTIPLE_CHOICE_INPUT -> {

      }
      FRACTION_INPUT -> {

      }
      NUMERIC_INPUT -> {

      }
      NUMERIC_WITH_UNITS -> {

      }
      TEXT_INPUT -> {

      }
    }
  }

  private fun updateProgressBar(currentQuestion: Int, totalQuestions: Int) {

  }

  fun handleSubmitButton() {

  }

  fun handleContinueButton() {

  }

  fun handleReplayButton() {

  }

  fun handleReturnToTopicButton() {

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
