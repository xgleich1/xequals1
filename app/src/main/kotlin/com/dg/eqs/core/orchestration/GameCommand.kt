package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.core.information.Info


sealed class GameCommand {
    data class ShowInfo(val info: Info) : GameCommand()

    data class ShowOrigin(val origin: AnyOrigin) : GameCommand()

    data class ShowStatus(val status: StringRes) : GameCommand()

    object ShowFinishedLayout : GameCommand()

    object HideFinishedLayout : GameCommand()

    object NavigateBack : GameCommand()
}