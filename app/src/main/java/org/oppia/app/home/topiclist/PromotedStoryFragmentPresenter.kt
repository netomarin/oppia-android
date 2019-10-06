package org.oppia.app.home.topiclist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.oppia.app.databinding.PromotedStoryFragmentBinding
import org.oppia.app.viewmodel.ViewModelProvider
import javax.inject.Inject

/** Presenter for [PromotedStoryFragment]. */
class PromotedStoryFragmentPresenter @Inject constructor(
  private val fragment: Fragment,
  private val viewModelProvider: ViewModelProvider<PromotedStoryViewModel>
  ) {
  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    val binding = PromotedStoryFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    binding.let {
      it.viewModel = getPromotedStoryViewModel()
      it.lifecycleOwner = fragment
    }
    return binding.root
  }

  private fun getPromotedStoryViewModel(): PromotedStoryViewModel {
    return viewModelProvider.getForFragment(fragment, PromotedStoryViewModel::class.java)
  }
}
