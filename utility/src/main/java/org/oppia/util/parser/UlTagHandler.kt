package org.oppia.util.parser

import android.text.Editable
import android.text.Html
import android.text.Html.TagHandler
import org.xml.sax.XMLReader
import android.R.attr.tag
import android.text.style.LeadingMarginSpan
import android.text.style.BulletSpan

class UlTagHandler : TagHandler {
  private var m_index = 0
  private val m_parents = ArrayList<String>()

  override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
    if (tag == "ul" || tag == "ol" || tag == "dd") {
      if (opening) {
        m_parents.add(tag)
      } else
        m_parents.remove(tag)

      m_index = 0
    } else if (tag == "li" && !opening) handleListTag(output)
  }

  private fun handleListTag(output: Editable) {
    if (m_parents[m_parents.size - 1] == "ul") {
      output.append("\n")
      val split = output.toString().split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

      val lastIndex = split.size - 1
      val start = output.length - split[lastIndex].length - 1
      output.setSpan(BulletSpan(15 * m_parents.size), start, output.length, 0)
    } else if (m_parents[m_parents.size - 1] == "ol") {
      m_index++

      output.append("\n")
      val split = output.toString().split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

      val lastIndex = split.size - 1
      val start = output.length - split[lastIndex].length - 1
      output.insert(start, "$m_index. ")
      output.setSpan(LeadingMarginSpan.Standard(15 * m_parents.size), start, output.length, 0)
    }
  }
}
