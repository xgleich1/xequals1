package com.dg.eqs.core.interaction.scrolling


sealed class ScrollingCommand {
    object ShowGridLines : ScrollingCommand()

    data class ScrollGridX(val amountX: Int) : ScrollingCommand()

    data class ScrollGridY(val amountY: Int) : ScrollingCommand()

    object HideGridLines : ScrollingCommand()
}