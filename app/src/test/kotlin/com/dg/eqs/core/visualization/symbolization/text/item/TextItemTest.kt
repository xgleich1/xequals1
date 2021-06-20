package com.dg.eqs.core.visualization.symbolization.text.item

import com.dg.eqs.base.abbreviation.AnyTextItem
import com.dg.eqs.base.composition.Size
import com.dg.eqs.core.definition.term.item.Item
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.INITIAL
import com.dg.eqs.classes.font
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test


class TextItemTest {
    @Test
    fun `A text item returns itself when it's touched`() {
        // GIVEN
        val textItem = textItem()

        // WHEN
        val touched = textItem.touch(touch(
                hitTouchX(textItem),
                hitTouchY(textItem)))

        // THEN
        assertTrue(touched === textItem)
    }

    @Test
    fun `A text item returns null when it's not touched`() {
        // GIVEN
        val textItem = textItem()

        // WHEN
        val touched = textItem.touch(touch(
                missTouchX(textItem),
                missTouchY(textItem)))

        // THEN
        assertNull(touched)
    }

    @Test
    fun `Should pad a text item's width to separate its text from other symbols`() {
        // GIVEN
        val textItem = textItem()

        // WHEN
        textItem.draft(pencil(
                font(textSize = Size(45, 0)),
                textItemWidthPadding = 10,
                textItemMinSize = 50))

        // THEN
        assertThat(textItem.width).isEqualTo(55)
    }

    @Test
    fun `Should always pad a text item to a minimum width to make it easy to touch`() {
        // GIVEN
        val textItem = textItem()

        // WHEN
        textItem.draft(pencil(
                font(textSize = Size(35, 0)),
                textItemWidthPadding = 10,
                textItemMinSize = 50))

        // THEN
        assertThat(textItem.width).isEqualTo(50)
    }

    @Test
    fun `Should pad a text item's height to separate its text from other symbols`() {
        // GIVEN
        val textItem = textItem()

        // WHEN
        textItem.draft(pencil(
                font(textSize = Size(0, 45)),
                textItemHeightPadding = 10,
                textItemMinSize = 50))

        // THEN
        assertThat(textItem.height).isEqualTo(55)
    }

    @Test
    fun `Should always pad a text item to a minimum height to make it easy to touch`() {
        // GIVEN
        val textItem = textItem()

        // WHEN
        textItem.draft(pencil(
                font(textSize = Size(0, 35)),
                textItemHeightPadding = 10,
                textItemMinSize = 50))

        // THEN
        assertThat(textItem.height).isEqualTo(50)
    }

    private fun touch(x: Int, y: Int) = Touch(x, y, INITIAL)

    private fun hitTouchX(textItem: AnyTextItem) = textItem.centerX

    private fun hitTouchY(textItem: AnyTextItem) = textItem.centerY

    private fun missTouchX(textItem: AnyTextItem) = textItem.firstX - 1

    private fun missTouchY(textItem: AnyTextItem) = textItem.firstY - 1

    private fun textItem(origin: Item = mock(), text: String = "") = TestAnyTextItem(origin, text)

    private class TestAnyTextItem(origin: Item, text: String) : TextItem<Item>(origin, text)
}