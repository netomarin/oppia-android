package org.oppia.app.home.topiclist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.oppia.app.databinding.TopicListFragmentBinding
import org.oppia.app.databinding.TopicSummaryViewBinding
import org.oppia.app.home.RouteToTopicListener
import org.oppia.app.model.TopicSummary
import org.oppia.app.recyclerview.BindableAdapter
import org.oppia.app.viewmodel.ViewModelProvider
import javax.inject.Inject

/** Presenter for [TopicListFragment]. */
class TopicListFragmentPresenter @Inject constructor(
  activity: AppCompatActivity,
  private val fragment: Fragment,
  private val viewModelProvider: ViewModelProvider<TopicListViewModel>
) {
  private val routeToTopicListener = activity as RouteToTopicListener

  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
    val binding = TopicListFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    binding.topicListRecyclerView.apply {
      adapter = createRecyclerViewAdapter()
      // https://stackoverflow.com/a/50075019/3689782
      layoutManager = GridLayoutManager(context, /* spanCount= */ 2)
    }
    binding.let {
      it.viewModel = getTopicListViewModel()
      it.lifecycleOwner = fragment
    }
    return binding.root
  }

  fun onTopicSummaryClicked(topicSummary: TopicSummary) {
    routeToTopicListener.routeToTopic(topicSummary.topicId)
  }

  private fun createRecyclerViewAdapter(): BindableAdapter<TopicSummaryViewModel> {
    return BindableAdapter.Builder
      .newBuilder<TopicSummaryViewModel>()
      .registerViewDataBinder(
        inflateDataBinding = TopicSummaryViewBinding::inflate,
        setViewModel = TopicSummaryViewBinding::setViewModel)
      .build()
  }

  private fun getTopicListViewModel(): TopicListViewModel {
    return viewModelProvider.getForFragment(fragment, TopicListViewModel::class.java)
  }
}
