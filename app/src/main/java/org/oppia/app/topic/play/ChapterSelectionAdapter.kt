package org.oppia.app.topic.play

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import org.oppia.app.R
import org.oppia.app.databinding.PlayChapterViewBinding
import org.oppia.app.model.ChapterSummary

// TODO(#216): Make use of generic data-binding-enabled RecyclerView adapter.
/** Adapter to bind chapters to [RecyclerView] inside [TopicPlayFragment]. */
class ChapterSelectionAdapter(private val chapterList: MutableList<ChapterSummary>) : BaseAdapter() {
  @SuppressLint("ViewHolder")
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

    Log.d("TAG", "getView")

    val chapterListItemBinding = DataBindingUtil.inflate<PlayChapterViewBinding>(
      LayoutInflater.from(parent!!.context),
      R.layout.play_chapter_view, parent,
      /* attachToRoot= */ false
    )

    chapterListItemBinding.setVariable(BR.isChapterCompleted, true)
    chapterListItemBinding.setVariable(BR.chapter, chapterList[position])
    return chapterListItemBinding!!.root

  }

  override fun getItem(position: Int): ChapterSummary {
    return chapterList[position]
  }

  override fun getItemId(position: Int): Long {
    return 0
  }

  override fun getCount(): Int {
    return chapterList.size
  }

//  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
//    val chapterListItemBinding = DataBindingUtil.inflate<PlayChapterViewBinding>(
//      LayoutInflater.from(parent.context),
//      R.layout.play_chapter_view, parent,
//      /* attachToRoot= */ false
//    )
//    return ChapterViewHolder(chapterListItemBinding)
//  }
//
//  override fun onBindViewHolder(chapterViewHolder: ChapterViewHolder, i: Int) {
//    chapterViewHolder.bind(chapterList[i], i)
//  }
//
//  override fun getItemCount(): Int {
//    return chapterList.size
//  }
//
//  inner class ChapterViewHolder(val binding: PlayChapterViewBinding) :  RecyclerView.ViewHolder(binding.root) {
//    internal fun bind(chapter: ChapterSummary, position: Int) {
//
//      Log.d("TAG", "ChapterViewHolder: Chapter: " + chapter.name)
//
//      binding.setVariable(BR.isChapterCompleted, true)
//      binding.setVariable(BR.chapter, chapter)
//    }
//  }
}
