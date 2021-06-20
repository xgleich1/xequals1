package com.dg.eqs.core.visualization

import com.dg.eqs.base.composition.Font


class DraftPanelPencil(
        textSymbolFont: Font,
        textItemWidthPadding: Int,
        textItemHeightPadding: Int,
        textItemMinSize: Int,
        lineSymbolHeight: Int,
        bracketDraftPaddingLeft: Int,
        bracketDraftPaddingRight: Int,
        negationDraftPaddingLeft: Int,
        divisionDraftPaddingLeft: Int,
        divisionDraftPaddingRight: Int)
    : Pencil(
        textSymbolFont,
        textItemWidthPadding,
        textItemHeightPadding,
        textItemMinSize,
        lineSymbolHeight,
        bracketDraftPaddingLeft,
        bracketDraftPaddingRight,
        negationDraftPaddingLeft,
        divisionDraftPaddingLeft,
        divisionDraftPaddingRight)