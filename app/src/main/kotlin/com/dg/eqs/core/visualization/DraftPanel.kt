package com.dg.eqs.core.visualization

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.content.res.getDimensionPixelSizeOrThrow
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.withStyledAttributes
import androidx.core.view.doOnPreDraw
import com.dg.eqs.R.styleable.*
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.extension.applicationComponent
import com.dg.eqs.base.extension.getFont
import com.dg.eqs.base.extension.halfHeight
import com.dg.eqs.base.extension.halfWidth
import com.dg.eqs.base.injection.module.DraftPanelModule
import com.dg.eqs.core.visualization.symbolization.SymbolPanel
import javax.inject.Inject


class DraftPanel(context: Context, attrs: AttributeSet)
    : SymbolPanel(context, attrs) {

    @Inject
    lateinit var draftPanelDrafter: DraftPanelDrafter


    init {
        context.withStyledAttributes(attrs, DraftPanel) {
            injectDependencies(createDraftPanelPencil())
        }
    }

    fun showDraft(origin: AnyOrigin) {
        doOnPreDraw {
            val draft = draftPanelDrafter.draft(origin)

            // ---------------------------------------------------
            // The draft created from the origin is scaled and
            // positioned equaling the CENTER_INSIDE behaviour
            // ---------------------------------------------------
            if(draft.width > width) {
                draft.scale(width.toFloat() / draft.width)
            }

            if(draft.height > height) {
                draft.scale(height.toFloat() / draft.height)
            }

            draft.moveX(halfWidth - draft.centerX)
            draft.moveY(halfHeight - draft.centerY)

            showSymbolViews(draft.unravel())
        }
    }

    private fun TypedArray.createDraftPanelPencil(): DraftPanelPencil {
        val textSymbolFont = getFont(
                getResourceIdOrThrow(
                        DraftPanel_draftPanelTextSymbolFontId),
                getDimensionOrThrow(
                        DraftPanel_draftPanelTextSymbolFontSize))

        val textItemWidthPadding = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelTextItemWidthPadding)

        val textItemHeightPadding = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelTextItemHeightPadding)

        val textItemMinSize = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelTextItemMinSize)

        val lineSymbolHeight = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelLineSymbolHeight)

        val bracketDraftPaddingLeft = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelBracketDraftPaddingLeft)

        val bracketDraftPaddingRight = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelBracketDraftPaddingRight)

        val negationDraftPaddingLeft = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelNegationDraftPaddingLeft)

        val divisionDraftPaddingLeft = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelDivisionDraftPaddingLeft)

        val divisionDraftPaddingRight = getDimensionPixelSizeOrThrow(
                DraftPanel_draftPanelDivisionDraftPaddingRight)

        return DraftPanelPencil(
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
    }

    private fun injectDependencies(draftPanelPencil: DraftPanelPencil) =
            applicationComponent
                    .draftPanelComponentBuilder()
                    .draftPanelModule(DraftPanelModule(draftPanelPencil))
                    .build()
                    .inject(this)
}