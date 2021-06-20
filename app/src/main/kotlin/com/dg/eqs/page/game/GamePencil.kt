package com.dg.eqs.page.game

import android.app.Application
import com.dg.eqs.R
import com.dg.eqs.base.extension.getDimensionInInt
import com.dg.eqs.base.extension.getFont
import com.dg.eqs.core.visualization.Pencil


class GamePencil(application: Application) : Pencil(
        application.getFont(R.font.font_eqs, R.dimen.global_text_symbol_font_size_page),
        application.getDimensionInInt(R.dimen.global_text_item_width_padding_page),
        application.getDimensionInInt(R.dimen.global_text_item_height_padding_page),
        application.getDimensionInInt(R.dimen.global_text_item_min_size_page),
        application.getDimensionInInt(R.dimen.global_line_symbol_height_page),
        application.getDimensionInInt(R.dimen.global_bracket_draft_padding_left_page),
        application.getDimensionInInt(R.dimen.global_bracket_draft_padding_right_page),
        application.getDimensionInInt(R.dimen.global_negation_draft_padding_left_page),
        application.getDimensionInInt(R.dimen.global_division_draft_padding_left_page),
        application.getDimensionInInt(R.dimen.global_division_draft_padding_right_page))