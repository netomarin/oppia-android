package org.oppia.app.topic.questionplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import org.oppia.app.databinding.QuestionPlayerFragmentBinding
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.EphemeralQuestion
import org.oppia.app.model.EphemeralState
import org.oppia.app.viewmodel.ViewModelProvider
import org.oppia.app.player.state.*
import org.oppia.domain.question.QuestionAssessmentProgressController
import org.oppia.domain.question.QuestionTrainingController
import org.oppia.util.data.AsyncResult
import org.oppia.util.logging.Logger
import javax.inject.Inject

/** The presenter for [QuestionPlayerFragment]. */
@FragmentScope
class QuestionPlayerFragmentPresenter @Inject constructor(
  private val activity: AppCompatActivity,
  private val fragment: Fragment,
  private val logger: Logger,
  private val viewModelProvider: ViewModelProvider<QuestionPlayerViewModel>,
  private val questionTrainingController: QuestionTrainingController,
  private val questionAssessmentProgressController: QuestionAssessmentProgressController
) {

  private lateinit var stateAdapter: StateAdapter
  private val itemList: MutableList<Any> = ArrayList()

  private val viewModel by lazy {
    getQuestionPlayerViewModel()
  }

  private lateinit var currentEphemeralQuestion: EphemeralQuestion
  val numCorrectQuestions = 0

  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    val binding = QuestionPlayerFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    binding.let {
      it.lifecycleOwner = fragment
      it.presenter = this
      it.viewModel = viewModel
    }
    stateAdapter = StateAdapter(itemList, null)
    subscribeToCurrentState()
    return binding.root
  }

  private fun subscribeToCurrentState() {
    Transformations.map(questionAssessmentProgressController.getCurrentQuestion(), ::processEphemeralQuestionResult)
      .observe(fragment, Observer { result->
        itemList.clear()
        currentEphemeralQuestion = result
        val currentQuestion = result.currentQuestionIndex
        val totalQuestions = result.totalQuestionCount
        updateInteractionInputByState(result.ephemeralState)
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

  private fun onInteractionButtonClick() {

  }

  private fun endQuestionTraining() {
    questionTrainingController.stopQuestionTrainingSession().observe(fragment, Observer {
      if (it.isSuccess()) {
        (activity as QuestionPlayerActivity).finish()
      }
    })
  }

  private fun hideKeyboard() {
    val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(fragment.view!!.windowToken, InputMethodManager.SHOW_FORCED)
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
