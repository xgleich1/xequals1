package com.dg.eqs.base.purchasing

import android.content.res.Resources
import androidx.annotation.StringRes
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.SkuType.INAPP


class BillingParamsBuilder(
        private val resources: Resources) {

    fun buildInAppSkuDetailsParams(@StringRes vararg skuResIds: Int): SkuDetailsParams =
            SkuDetailsParams
                    .newBuilder()
                    .setType(INAPP)
                    .setSkusList(skuResIds.map(resources::getString))
                    .build()

    fun buildBillingFlowParams(skuDetails: SkuDetails): BillingFlowParams =
            BillingFlowParams
                    .newBuilder()
                    .setSkuDetails(skuDetails)
                    .build()

    fun buildConsumeParams(purchase: Purchase): ConsumeParams =
            ConsumeParams
                    .newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()
}