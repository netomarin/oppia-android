package org.oppia.app.testing

import android.os.Bundle
import android.text.Spannable
import android.widget.TextView
import org.oppia.app.R
import org.oppia.app.activity.InjectableAppCompatActivity
import org.oppia.util.parser.HtmlParser
import javax.inject.Inject

/** This is a dummy activity to test Html parsing. */
class HtmlParserTestActivity : InjectableAppCompatActivity() {
  @Inject
  lateinit var htmlParserFactory: HtmlParser.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent.inject(this)
    setContentView(R.layout.test_html_parser_activity)

    val testHtmlContentTextView: TextView = findViewById(R.id.test_html_content_text_view)
    val rawDummyString =
      "\u003cp\u003e\"Let's try one last question,\" said Mr. Baker. \"Here's a pineapple cake cut into pieces.\"\u003c/p\u003e\u003coppia-noninteractive-image alt-with-value=\"\u0026amp;quot;Pineapple cake with 7/9 having cherries.\u0026amp;quot;\" caption-with-value=\"\u0026amp;quot;\u0026amp;quot;\" filepath-with-value=\"\u0026amp;quot;pineapple_cake_height_479_width_480.png\u0026amp;quot;\"\u003e\u003c/oppia-noninteractive-image\u003e\u003cp\u003e\u00a0\u003c/p\u003e\u003cp\u003e\u003cstrong\u003eQuestion 6\u003c/strong\u003e: What fraction of the cake has big red cherries in the pineapple slices?\u003c/p\u003e" +
          "<p>Meet Matthew!</p><oppia-noninteractive-image alt-with-value=\\\"&amp;quot;A boy with a red shirt and blue trousers.&amp;quot;\\\" caption-with-value=\\\"&amp;quot;&amp;quot;\\\" filepath-with-value=\\\"&amp;quot;img_20180121_113315_pqwqhf863w_height_565_width_343.png&amp;quot;\\\"></oppia-noninteractive-image><p>Matthew is a young man who likes friends, sports, and eating cake! He also likes learning new things. We’ll follow Matthew as he learns about fractions and how to use them.<br></p><p>You should know the following before going on:<br></p><ul><li>The counting numbers (1, 2, 3, 4, 5 ….)<br></li><li>How to tell whether one counting number is bigger or smaller than another<br></li></ul><p>You should also get some paper and a pen or pencil to write with, and find a quiet place to work. Take your time, and go through the story at your own pace. Understanding is more important than speed!<br></p><p>Once you’re ready, click <strong>Continue</strong> to get started!<br></p>\"\n" +
          " "
    val htmlResult: Spannable =
      htmlParserFactory.create( /* entityType= */ "exploration", /* entityId= */ "oppia", /* imageCenterAlign= */ false)
        .parseOppiaHtml(
          rawDummyString,
          testHtmlContentTextView
        )
    testHtmlContentTextView.text = htmlResult
  }
}
