package org.oppia.app.profile

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import org.oppia.app.fragment.InjectableDialogFragment
import javax.inject.Inject

const val KEY_ADMIN_SETTINGS_PIN = "ADMIN_SETTINGS_PIN"

class AdminSettingsDialogFragment : InjectableDialogFragment() {
  companion object {
    fun newInstance(adminPin: String): AdminSettingsDialogFragment {
      val adminSettingDialogFragment = AdminSettingsDialogFragment()
      val args = Bundle()
      args.putString(KEY_ADMIN_SETTINGS_PIN, adminPin)
      adminSettingDialogFragment.arguments = args
      return adminSettingDialogFragment
    }
  }
  @Inject lateinit var adminSettingsDialogFragmentPresenter: AdminSettingsDialogFragmentPresenter

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    fragmentComponent.inject(this)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return adminSettingsDialogFragmentPresenter.handleOnCreateDialog(activity as ProfileRouteDialogInterface)
  }
}