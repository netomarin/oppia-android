package org.oppia.app.home.topiclist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.oppia.app.fragment.InjectableFragment
import javax.inject.Inject

/** Fragment corresponding to the promoted (recommended or continued) story on the home page. */
class PromotedStoryFragment : InjectableFragment() {
  @Inject
  lateinit var promotedStoryFragmentPresenter: PromotedStoryFragmentPresenter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    fragmentComponent.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return promotedStoryFragmentPresenter.handleCreateView(inflater, container)
  }
}
