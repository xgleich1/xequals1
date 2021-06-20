package com.dg.eqs.classes

import com.dg.eqs.base.composition.Font
import com.dg.eqs.core.visualization.Pencil


fun pencil(
        textItemWidthPadding: Int = 0,
        textItemHeightPadding: Int = 0,
        textItemMinSize: Int = 0,
        lineSymbolHeight: Int = 0,
        bracketDraftPaddingLeft: Int = 0,
        bracketDraftPaddingRight: Int = 0,
        negationDraftPaddingLeft: Int = 0,
        divisionDraftPaddingLeft: Int = 0,
        divisionDraftPaddingRight: Int = 0): Pencil = pencil(
        font(),
        textItemWidthPadding,
        textItemHeightPadding,
        textItemMinSize,
        lineSymbolHeight,
        bracketDraftPaddingLeft,
        bracketDraftPaddingRight,
        negationDraftPaddingLeft,
        divisionDraftPaddingLeft,
        divisionDraftPaddingRight)

fun pencil(
        textSymbolFont: Font = font(),
        textItemWidthPadding: Int = 0,
        textItemHeightPadding: Int = 0,
        textItemMinSize: Int = 0,
        lineSymbolHeight: Int = 0,
        bracketDraftPaddingLeft: Int = 0,
        bracketDraftPaddingRight: Int = 0,
        negationDraftPaddingLeft: Int = 0,
        divisionDraftPaddingLeft: Int = 0,
        divisionDraftPaddingRight: Int = 0): Pencil = TestPencil(
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

private class TestPencil(
        textSymbolFont: Font,
        textItemWidthPadding: Int,
        textItemHeightPadding: Int,
        textItemMinSize: Int,
        lineSymbolHeight: Int,
        bracketDraftPaddingLeft: Int,
        bracketDraftPaddingRight: Int,
        negationDraftPaddingLeft: Int,
        divisionDraftPaddingLeft: Int,
        divisionDraftPaddingRight: Int) : Pencil(
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