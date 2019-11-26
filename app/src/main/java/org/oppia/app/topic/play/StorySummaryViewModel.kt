package org.oppia.app.topic.play

import android.view.View
import androidx.lifecycle.ViewModel
import org.oppia.app.model.StorySummary
import org.oppia.app.viewmodel.ObservableViewModel

/** [ViewModel] for displaying a story summary. */
class StorySummaryViewModel(
  val storySummary: StorySummary,
  private val storySummarySelector: StorySummarySelector
) : ObservableViewModel() {
  fun clickOnStorySummaryTitle(@Suppress("UNUSED_PARAMETER") v: View) {
    storySummarySelector.selectStorySummary(storySummary)
  }
}
