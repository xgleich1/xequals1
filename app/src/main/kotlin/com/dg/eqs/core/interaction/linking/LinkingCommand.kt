package com.dg.eqs.core.interaction.linking

import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.interaction.Touch


sealed class LinkingCommand {
    data class ShowSourceMark(val source: TermDrafts) : LinkingCommand()

    data class ShowTargetMark(val target: TermDrafts) : LinkingCommand()

    data class ShowSourceArrow(val touch: Touch) : LinkingCommand()

    data class HandleLink(val link: Link) : LinkingCommand()

    object HideSourceMark : LinkingCommand()

    object HideTargetMark : LinkingCommand()

    object HideSourceArrow : LinkingCommand()
}