package com.dg.eqs.core.interaction.scrolling

import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.*
import com.dg.eqs.core.interaction.scrolling.ScrollingCommand.*
import org.mockito.kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ScrollingCommanderTest {
    @Mock
    private lateinit var observer: (ScrollingCommand) -> Unit

    private lateinit var scrollingCommander: ScrollingCommander


    @Before
    fun setUp() {
        scrollingCommander = ScrollingCommander()
        scrollingCommander.commands.addObserver(observer)

        scrollingCommander.onGridLaidOut(Size(1000, 800))
    }

    @Test
    fun `Should command the observer to show the grid lines on an initial touch`() {
        // WHEN
        val draft = draft()
        val initialTouch = initialTouch()

        // WHEN
        scrollingCommander.onGridTouched(draft, initialTouch)

        // THEN
        verify(observer).invoke(ShowGridLines)
    }

    @Test
    fun `An initial touch has no effect when its hitting the draft`() {
        // WHEN
        val draft = draft()
        val initialTouch = initialTouch()

        whenever(draft.isTouched(initialTouch)) doReturn true

        // WHEN
        scrollingCommander.onGridTouched(draft, initialTouch)

        // THEN
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should command the observer to scroll the grid on an ensuing touch`() {
        // WHEN
        val draft = draft()

        scrollingCommander.onGridTouched(draft, initialTouch(5, 10))

        // WHEN
        scrollingCommander.onGridTouched(draft, ensuingTouch(15, 30))

        // THEN
        verify(observer).invoke(ScrollGridX(10))
        verify(observer).invoke(ScrollGridY(20))
    }

    @Test
    fun `Should command the observer to scroll the grid on subsequent ensuing touches`() {
        // WHEN
        val draft = draft()

        scrollingCommander.onGridTouched(draft, initialTouch(5, 10))
        scrollingCommander.onGridTouched(draft, ensuingTouch(15, 30))

        // WHEN
        scrollingCommander.onGridTouched(draft, ensuingTouch(40, 60))

        // THEN
        verify(observer).invoke(ScrollGridX(25))
        verify(observer).invoke(ScrollGridY(30))
    }

    @Test
    fun `An ensuing touch has no effect when the initial one hit the draft`() {
        // WHEN
        val draft = draft()
        val initialTouch = initialTouch()

        whenever(draft.isTouched(initialTouch)) doReturn true

        scrollingCommander.onGridTouched(draft, initialTouch)

        // WHEN
        scrollingCommander.onGridTouched(draft, ensuingTouch())

        // THEN
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should command the observer to hide the grid lines on an ceasing touch`() {
        // WHEN
        val draft = draft()

        scrollingCommander.onGridTouched(draft, initialTouch())

        // WHEN
        scrollingCommander.onGridTouched(draft, ceasingTouch())

        // THEN
        verify(observer).invoke(HideGridLines)
    }

    @Test
    fun `A ceasing touch has no effect when the initial one hit the draft`() {
        // WHEN
        val draft = draft()
        val initialTouch = initialTouch()

        whenever(draft.isTouched(initialTouch)) doReturn true

        scrollingCommander.onGridTouched(draft, initialTouch)

        // WHEN
        scrollingCommander.onGridTouched(draft, ceasingTouch())

        // THEN
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should command the observer to hide the grid lines on an aborted touch`() {
        // WHEN
        val draft = draft()

        scrollingCommander.onGridTouched(draft, initialTouch())

        // WHEN
        scrollingCommander.onGridTouched(draft, abortedTouch())

        // THEN
        verify(observer).invoke(HideGridLines)
    }

    @Test
    fun `An aborted touch has no effect when the initial one hit the draft`() {
        // WHEN
        val draft = draft()
        val initialTouch = initialTouch()

        whenever(draft.isTouched(initialTouch)) doReturn true

        scrollingCommander.onGridTouched(draft, initialTouch)

        // WHEN
        scrollingCommander.onGridTouched(draft, abortedTouch())

        // THEN
        verify(observer, never()).invoke(any())
    }

    @Test
    fun `Should command the observer to show the grid lines following an new initial touch`() {
        // WHEN
        val draft = draft()

        scrollingCommander.onGridTouched(draft, initialTouch())
        scrollingCommander.onGridTouched(draft, ensuingTouch())
        scrollingCommander.onGridTouched(draft, ceasingTouch())

        // WHEN
        scrollingCommander.onGridTouched(draft, initialTouch())

        // THEN
        verify(observer, times(2)).invoke(ShowGridLines)
    }

    //<editor-fold desc="Command scrolling to the left">
    @Test
    fun `Should command the observer to scroll the grid with a draft smaller and completely inside of it to the left`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 250,
                finalX = 750)

        // WHEN
        onGridTouched(draft, touchDistanceX = -200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -200)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft smaller and completely inside of it to the left until the draft hits the edge`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 250,
                finalX = 750)

        // WHEN
        onGridTouched(draft, touchDistanceX = -300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -250)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft smaller and completely inside of it to the left when the draft is already hitting the left edge`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 0,
                finalX = 500)

        // WHEN
        onGridTouched(draft, touchDistanceX = -200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft smaller and partly outside of it on the right to the left back inside`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 750,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = -300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -300)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft smaller and partly outside of it on the left further to the left`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = -250,
                finalX = 250)

        // WHEN
        onGridTouched(draft, touchDistanceX = -200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on both edges to the left`() {
        // GIVEN
        val draft = draft(width = 1500,
                firstX = -250,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = -200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -200)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on both edges to the left until the draft hits the right edge`() {
        // GIVEN
        val draft = draft(width = 1500,
                firstX = -250,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = -300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -250)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft wider and partly outside of it on the left to the left when the draft is already hitting the right edge`() {
        // GIVEN
        val draft = draft(width = 1500,
                firstX = -500,
                finalX = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceX = -200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on the right to the left`() {
        // GIVEN
        val draft = draft(width = 1150,
                firstX = 100,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = -200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -200)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on the right to the left until the draft hits the right edge`() {
        // GIVEN
        val draft = draft(width = 1150,
                firstX = 100,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = -300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = -250)
    }
    //</editor-fold>

    //<editor-fold desc="Command scrolling to the right">
    @Test
    fun `Should command the observer to scroll the grid with a draft smaller and completely inside of it to the right`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 250,
                finalX = 750)

        // WHEN
        onGridTouched(draft, touchDistanceX = 200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 200)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft smaller and completely inside of it to the right until the draft hits the edge`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 250,
                finalX = 750)

        // WHEN
        onGridTouched(draft, touchDistanceX = 300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 250)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft smaller and completely inside of it to the right when the draft is already hitting the right edge`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 500,
                finalX = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceX = 200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft smaller and partly outside of it on the left to the right back inside`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = -250,
                finalX = 250)

        // WHEN
        onGridTouched(draft, touchDistanceX = 300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 300)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft smaller and partly outside of it on the right further to the right`() {
        // GIVEN
        val draft = draft(width = 500,
                firstX = 750,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = 200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on both edges to the right`() {
        // GIVEN
        val draft = draft(width = 1500,
                firstX = -250,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = 200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 200)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on both edges to the right until the draft hits the left edge`() {
        // GIVEN
        val draft = draft(width = 1500,
                firstX = -250,
                finalX = 1250)

        // WHEN
        onGridTouched(draft, touchDistanceX = 300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 250)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft wider and partly outside of it on the right to the right when the draft is already hitting the left edge`() {
        // GIVEN
        val draft = draft(width = 1500,
                firstX = 0,
                finalX = 1500)

        // WHEN
        onGridTouched(draft, touchDistanceX = 200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on the left to the right`() {
        // GIVEN
        val draft = draft(width = 1150,
                firstX = -250,
                finalX = 900)

        // WHEN
        onGridTouched(draft, touchDistanceX = 200)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 200)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft wider and partly outside of it on the left to the right until the draft hits the left edge`() {
        // GIVEN
        val draft = draft(width = 1150,
                firstX = -250,
                finalX = 900)

        // WHEN
        onGridTouched(draft, touchDistanceX = 300)

        // THEN
        verifyScrollingCompleted(scrollAmountX = 250)
    }
    //</editor-fold>

    //<editor-fold desc="Command scrolling to the top">
    @Test
    fun `Should command the observer to scroll the grid with a draft shorter and completely inside of it to the top`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 200,
                finalY = 600)

        // WHEN
        onGridTouched(draft, touchDistanceY = -150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -150)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft shorter and completely inside of it to the top until the draft hits the edge`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 200,
                finalY = 600)

        // WHEN
        onGridTouched(draft, touchDistanceY = -250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -200)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft shorter and completely inside of it to the top when the draft is already hitting the top edge`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 0,
                finalY = 400)

        // WHEN
        onGridTouched(draft, touchDistanceY = -150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft shorter and partly outside of it on the bottom to the top back inside`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 600,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = -250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -250)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft shorter and partly outside of it on the top further to the top`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = -200,
                finalY = 200)

        // WHEN
        onGridTouched(draft, touchDistanceY = -150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on both edges to the top`() {
        // GIVEN
        val draft = draft(height = 1200,
                firstY = -200,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = -150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -150)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on both edges to the top until the draft hits the bottom edge`() {
        // GIVEN
        val draft = draft(height = 1200,
                firstY = -200,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = -250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -200)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft greater and partly outside of it on the top to the top when the draft is already hitting the bottom edge`() {
        // GIVEN
        val draft = draft(height = 1200,
                firstY = -400,
                finalY = 800)

        // WHEN
        onGridTouched(draft, touchDistanceY = -150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on the bottom to the top`() {
        // GIVEN
        val draft = draft(height = 900,
                firstY = 100,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = -150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -150)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on the bottom to the top until the draft hits the bottom edge`() {
        // GIVEN
        val draft = draft(height = 900,
                firstY = 100,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = -250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = -200)
    }
    //</editor-fold>

    //<editor-fold desc="Command scrolling to the bottom">
    @Test
    fun `Should command the observer to scroll the grid with a draft shorter and completely inside of it to the bottom`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 200,
                finalY = 600)

        // WHEN
        onGridTouched(draft, touchDistanceY = 150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 150)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft shorter and completely inside of it to the bottom until the draft hits the edge`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 200,
                finalY = 600)

        // WHEN
        onGridTouched(draft, touchDistanceY = 250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 200)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft shorter and completely inside of it to the bottom when the draft is already hitting the bottom edge`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 400,
                finalY = 800)

        // WHEN
        onGridTouched(draft, touchDistanceY = 150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft shorter and partly outside of it on the top to the bottom back inside`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = -200,
                finalY = 200)

        // WHEN
        onGridTouched(draft, touchDistanceY = 250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 250)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft shorter and partly outside of it on the bottom further to the bottom`() {
        // GIVEN
        val draft = draft(height = 400,
                firstY = 600,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = 150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on both edges to the bottom`() {
        // GIVEN
        val draft = draft(height = 1200,
                firstY = -200,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = 150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 150)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on both edges to the bottom until the draft hits the top edge`() {
        // GIVEN
        val draft = draft(height = 1200,
                firstY = -200,
                finalY = 1000)

        // WHEN
        onGridTouched(draft, touchDistanceY = 250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 200)
    }

    @Test
    fun `Should not command the observer to scroll the grid with a draft greater and partly outside of it on the bottom to the bottom when the draft is already hitting the top edge`() {
        // GIVEN
        val draft = draft(height = 1200,
                firstY = 0,
                finalY = 1200)

        // WHEN
        onGridTouched(draft, touchDistanceY = 150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 0)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on the top to the bottom`() {
        // GIVEN
        val draft = draft(height = 900,
                firstY = -200,
                finalY = 700)

        // WHEN
        onGridTouched(draft, touchDistanceY = 150)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 150)
    }

    @Test
    fun `Should command the observer to scroll the grid with a draft greater and partly outside of it on the top to the bottom until the draft hits the top edge`() {
        // GIVEN
        val draft = draft(height = 900,
                firstY = -200,
                finalY = 700)

        // WHEN
        onGridTouched(draft, touchDistanceY = 250)

        // THEN
        verifyScrollingCompleted(scrollAmountY = 200)
    }
    //</editor-fold>

    private fun draft(
            width: Int = 0,
            height: Int = 0,
            firstX: Int = 0,
            firstY: Int = 0,
            finalX: Int = 0,
            finalY: Int = 0): AnyDraft = mock {

        on { it.width } doReturn width
        on { it.height } doReturn height
        on { it.firstX } doReturn firstX
        on { it.firstY } doReturn firstY
        on { it.finalX } doReturn finalX
        on { it.finalY } doReturn finalY
    }

    private fun initialTouch(x: Int = 0, y: Int = 0) = Touch(x, y, INITIAL)

    private fun ensuingTouch(x: Int = 0, y: Int = 0) = Touch(x, y, ENSUING)

    private fun ceasingTouch() = Touch(0, 0, CEASING)

    private fun abortedTouch() = Touch(0, 0, ABORTED)

    private fun onGridTouched(draft: AnyDraft, touchDistanceX: Int = 0, touchDistanceY: Int = 0) {
        scrollingCommander.onGridTouched(draft, initialTouch())
        scrollingCommander.onGridTouched(draft, ensuingTouch(touchDistanceX, touchDistanceY))
        scrollingCommander.onGridTouched(draft, ceasingTouch())
    }

    private fun verifyScrollingCompleted(scrollAmountX: Int = 0, scrollAmountY: Int = 0) {
        inOrder(observer) {
            verify(observer).invoke(ShowGridLines)
            verify(observer).invoke(ScrollGridX(scrollAmountX))
            verify(observer).invoke(ScrollGridY(scrollAmountY))
            verify(observer).invoke(HideGridLines)
        }

        verifyNoMoreInteractions(observer)
    }
}