package com.dg.eqs.base.extension

import android.graphics.Path
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.junit.Test


class PathExtTest {
    @Test
    fun `Should add a line to a path`() {
        // GIVEN
        val path: Path = mock()

        // WHEN
        path.addLine(1, 2, 3, 4)

        // THEN
        verify(path).moveTo(1f, 2f)
        verify(path).lineTo(3f, 4f)
    }

    @Test
    fun `Should add an arc to a path`() {
        // GIVEN
        val path: Path = mock()

        // WHEN
        path.addArc(1, 2, 3, 4, 5, 6, 7, 8)

        // THEN
        verify(path).moveTo(1f, 2f)
        verify(path).cubicTo(3f, 4f, 5f, 6f, 7f, 8f)
    }
}