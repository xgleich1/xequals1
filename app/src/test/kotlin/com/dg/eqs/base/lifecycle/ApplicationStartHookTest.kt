package com.dg.eqs.base.lifecycle

import com.dg.eqs.base.purchasing.BillingService
import com.dg.eqs.util.rules.MainDispatcherRule
import org.mockito.kotlin.verifyBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ApplicationStartHookTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var billingService: BillingService
    @InjectMocks
    private lateinit var applicationStartHook: ApplicationStartHook


    @Test
    fun `Should consume pending in-app purchases once the application is started`() {
        // WHEN
        applicationStartHook()

        // THEN
        verifyBlocking(billingService) { consumePendingInAppPurchases() }
    }
}