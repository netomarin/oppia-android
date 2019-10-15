package org.oppia.app.topic.questionplayer

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.oppia.app.fragment.FragmentScope
import javax.inject.Inject

/** [ViewModel] for concept card, providing rich text and worked examples */
@FragmentScope
class QuestionPlayerViewModel @Inject constructor() : ViewModel() {
  var isFeedbackVisible = ObservableField<Boolean>(false)
  var isSubmitButtonVisible = ObservableField<Boolean>(false)
  var isContinueButtonVisible = ObservableField<Boolean>(false)
  var isEndScreenVisible = ObservableField<Boolean>(false)
}