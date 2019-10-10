package org.oppia.app.topic.questionplayer

import androidx.lifecycle.ViewModel
import org.oppia.app.fragment.FragmentScope
import javax.inject.Inject

/** [ViewModel] for concept card, providing rich text and worked examples */
@FragmentScope
class QuestionPlayerViewModel @Inject constructor() : ViewModel() {
  fun getDummyText() = "hello world"
}