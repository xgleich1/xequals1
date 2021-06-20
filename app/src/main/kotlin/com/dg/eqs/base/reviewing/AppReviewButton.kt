package com.dg.eqs.base.reviewing

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri.parse
import android.util.AttributeSet
import com.dg.eqs.R
import com.dg.eqs.base.TileButton
import com.dg.eqs.base.extension.applicationComponent
import com.dg.eqs.base.extension.getString
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.persistence.offline.OfflinePersistence
import kotlinx.android.synthetic.main.button_tile.view.*
import javax.inject.Inject


class AppReviewButton(context: Context, attrs: AttributeSet)
    : TileButton(context, attrs) {

    @Inject
    lateinit var offlinePersistence: OfflinePersistence


    init {
        applicationComponent.inject(this)

        if(!loadAppReviewButtonClicked()) {
            showAppReviewLabel()
        } else {
            showAppReviewedLabel()
        }

        onClick {
            saveAppReviewButtonClicked()

            showAppReviewedLabel()

            openAppReview()
        }
    }

    private fun loadAppReviewButtonClicked() =
            offlinePersistence.loadBoolean(AppReviewButtonClickedKey, false)

    private fun saveAppReviewButtonClicked() =
            offlinePersistence.saveBoolean(AppReviewButtonClickedKey, true)

    private fun showAppReviewLabel() = label.setText(R.string.app_review_review)

    private fun showAppReviewedLabel() = label.setText(R.string.app_review_reviewed)

    private fun openAppReview() = try {
        val storeIntent = createAppReviewIntent(R.string.app_review_store_url)

        storeIntent.setPackage(getString(R.string.app_review_store_package))

        context.startActivity(storeIntent)
    } catch(e: ActivityNotFoundException) {
        val websiteIntent = createAppReviewIntent(R.string.app_review_website_url)

        context.startActivity(websiteIntent)
    }

    private fun createAppReviewIntent(url: Int) = Intent(ACTION_VIEW, parse(getString(url)))
}