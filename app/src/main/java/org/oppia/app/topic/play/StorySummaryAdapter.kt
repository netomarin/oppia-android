package org.oppia.app.topic.play

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.oppia.app.databinding.TopicPlayStorySummaryBinding
import org.oppia.app.model.ChapterPlayState
import org.oppia.app.model.ChapterSummary

// TODO(#216): Make use of generic data-binding-enabled RecyclerView adapter.

/** Adapter to bind StorySummary to [RecyclerView] inside [TopicPlayFragment]. */
class StorySummaryAdapter(
  private val itemList: MutableList<StorySummaryViewModel>,
  private val chapterSummarySelector: ChapterSummarySelector,
  private val expandedChapterListIndexListener: ExpandedChapterListIndexListener,
  private var currentExpandedChapterListIndex: Int?
) :
  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      TopicPlayStorySummaryBinding.inflate(
        inflater,
        parent,
        /* attachToParent= */ false
      )
    return StorySummaryViewHolder(binding)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, i: Int) {
    (holder as StorySummaryViewHolder).bind(itemList[i], i)
  }

  override fun getItemCount(): Int {
    return itemList.size
  }

  inner class StorySummaryViewHolder(private val binding: TopicPlayStorySummaryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    internal fun bind(storySummaryViewModel: StorySummaryViewModel, position: Int) {
      var isChapterListVisible = false
      if (currentExpandedChapterListIndex != null) {
        isChapterListVisible = currentExpandedChapterListIndex!! == position
      }
      binding.isListExpanded = isChapterListVisible
      binding.viewModel = storySummaryViewModel

      val chapterSummaries = storySummaryViewModel.storySummary.chapterList
      val completedChapterCount =
        chapterSummaries.map(ChapterSummary::getChapterPlayState)
          .filter {
            it == ChapterPlayState.COMPLETED
          }
          .size
      val storyPercentage: Int = (completedChapterCount * 100) / storySummaryViewModel.storySummary.chapterCount
      binding.storyPercentage = storyPercentage
      binding.storyProgressView.setStoryChapterDetails(
        storySummaryViewModel.storySummary.chapterCount,
        completedChapterCount
      )

      val chapterList = storySummaryViewModel.storySummary.chapterList
      binding.chapterRecyclerView.adapter = ChapterSummaryAdapter(chapterList, chapterSummarySelector)

      binding.root.setOnClickListener {
        val previousIndex: Int? = currentExpandedChapterListIndex
        currentExpandedChapterListIndex =
          if (currentExpandedChapterListIndex != null && currentExpandedChapterListIndex == position) {
            null
          } else {
            position
          }
        expandedChapterListIndexListener.onExpandListIconClicked(currentExpandedChapterListIndex)
        if (previousIndex != null && currentExpandedChapterListIndex != null && previousIndex == currentExpandedChapterListIndex) {
          notifyItemChanged(currentExpandedChapterListIndex!!)
        } else {
          if (previousIndex != null) {
            notifyItemChanged(previousIndex)
          }
          if (currentExpandedChapterListIndex != null) {
            notifyItemChanged(currentExpandedChapterListIndex!!)
          }
        }
      }
    }
  }
}
