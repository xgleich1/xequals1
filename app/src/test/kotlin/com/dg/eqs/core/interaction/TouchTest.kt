package com.dg.eqs.core.interaction

import android.view.MotionEvent
import android.view.MotionEvent.*
import com.dg.eqs.classes.draft
import com.dg.eqs.core.interaction.Touch.Action
import com.dg.eqs.core.interaction.Touch.Action.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TouchTest {
    @Test
    fun `Should create an initial touch from an down motion event`() {
        // GIVEN
        val motionEvent: MotionEvent = mock {
            on { x } doReturn 0f
            on { y } doReturn 1f
            on { action } doReturn ACTION_DOWN
        }

        // THEN
        assertThat(touch(motionEvent)).isEqualTo(touch(0, 1, INITIAL))
    }

    @Test
    fun `Should create an ensuing touch from a move motion event`() {
        // GIVEN
        val motionEvent: MotionEvent = mock {
            on { x } doReturn 2f
            on { y } doReturn 3f
            on { action } doReturn ACTION_MOVE
        }

        // THEN
        assertThat(touch(motionEvent)).isEqualTo(touch(2, 3, ENSUING))
    }

    @Test
    fun `Should create a ceasing touch from an up motion event`() {
        // GIVEN
        val motionEvent: MotionEvent = mock {
            on { x } doReturn 4f
            on { y } doReturn 5f
            on { action } doReturn ACTION_UP
        }

        // THEN
        assertThat(touch(motionEvent)).isEqualTo(touch(4, 5, CEASING))
    }

    @Test
    fun `Should create an aborted touch from an cancel motion event`() {
        // GIVEN
        val motionEvent: MotionEvent = mock {
            on { x } doReturn 6f
            on { y } doReturn 7f
            on { action } doReturn ACTION_CANCEL
        }

        // THEN
        assertThat(touch(motionEvent)).isEqualTo(touch(6, 7, ABORTED))
    }

    @Test
    fun `Should create an aborted touch from any other motion event`() {
        // GIVEN
        val motionEvent: MotionEvent = mock {
            on { x } doReturn 8f
            on { y } doReturn 9f
            on { action } doReturn -1
        }

        // THEN
        assertThat(touch(motionEvent)).isEqualTo(touch(8, 9, ABORTED))
    }

    @Test
    fun `A touch hits when its coordinates are inside a draft`() {
        // GIVEN
        val draft = draft(width = 100, height = 50, firstX = 25, firstY = 5)
        val touch = touch(x = 25, y = 5)

        // THEN
        assertTrue(touch hits draft)
    }

    @Test
    fun `A touch hits when its coordinates are a bit below a draft`() {
        // GIVEN
        val draft = draft(width = 100, height = 50, firstX = 25, firstY = 5)
        val touch = touch(x = 25, y = 5 + 50 + 50 / 2)

        // THEN
        assertTrue(touch hits draft)
    }

    @Test
    fun `A touch cannot hit a nonexistent draft`() = assertFalse(touch(0, 0) hits null)

    private fun touch(motionEvent: MotionEvent) = Touch(motionEvent)

    private fun touch(x: Int, y: Int, action: Action = INITIAL) = Touch(x, y, action)
}