package com.dg.eqs.core.visualization

import com.dg.eqs.base.composition.Font


abstract class Pencil(
        val textSymbolFont: Font,
        val textItemWidthPadding: Int,
        val textItemHeightPadding: Int,
        val textItemMinSize: Int,
        val lineSymbolHeight: Int,
        val bracketDraftPaddingLeft: Int,
        val bracketDraftPaddingRight: Int,
        val negationDraftPaddingLeft: Int,
        val divisionDraftPaddingLeft: Int,
        val divisionDraftPaddingRight: Int) {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Pencil

        if(textSymbolFont != other.textSymbolFont) return false
        if(textItemWidthPadding != other.textItemWidthPadding) return false
        if(textItemHeightPadding != other.textItemHeightPadding) return false
        if(textItemMinSize != other.textItemMinSize) return false
        if(lineSymbolHeight != other.lineSymbolHeight) return false
        if(bracketDraftPaddingLeft != other.bracketDraftPaddingLeft) return false
        if(bracketDraftPaddingRight != other.bracketDraftPaddingRight) return false
        if(negationDraftPaddingLeft != other.negationDraftPaddingLeft) return false
        if(divisionDraftPaddingLeft != other.divisionDraftPaddingLeft) return false
        if(divisionDraftPaddingRight != other.divisionDraftPaddingRight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = textSymbolFont.hashCode()

        result = 31 * result + textItemWidthPadding
        result = 31 * result + textItemHeightPadding
        result = 31 * result + textItemMinSize
        result = 31 * result + lineSymbolHeight
        result = 31 * result + bracketDraftPaddingLeft
        result = 31 * result + bracketDraftPaddingRight
        result = 31 * result + negationDraftPaddingLeft
        result = 31 * result + divisionDraftPaddingLeft
        result = 31 * result + divisionDraftPaddingRight

        return result
    }

    override fun toString() = "${javaClass.simpleName}(" +
                              "$textSymbolFont, " +
                              "$textItemWidthPadding, " +
                              "$textItemHeightPadding, " +
                              "$textItemMinSize, " +
                              "$lineSymbolHeight, " +
                              "$bracketDraftPaddingLeft, " +
                              "$bracketDraftPaddingRight, " +
                              "$negationDraftPaddingLeft, " +
                              "$divisionDraftPaddingLeft, " +
                              "$divisionDraftPaddingRight)"
}