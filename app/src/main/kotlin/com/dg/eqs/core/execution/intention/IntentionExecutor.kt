package com.dg.eqs.core.execution.intention

import com.dg.eqs.core.execution.intention.Intention.Click
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.click.ClickIntentionExecutor
import com.dg.eqs.core.execution.intention.link.LinkIntentionExecutor


class IntentionExecutor(
        private val linkIntentionExecutor: LinkIntentionExecutor,
        private val clickIntentionExecutor: ClickIntentionExecutor) {

    fun execute(intention: Intention) = when(intention) {
        is Link -> linkIntentionExecutor.execute(intention)
        is Click -> clickIntentionExecutor.execute(intention)
    }
}