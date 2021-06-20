package com.dg.eqs.base.extension

import android.content.Context
import android.content.res.Resources
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.ViewGroup.LayoutParams
import com.dg.eqs.Eqs
import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.injection.component.ApplicationComponent
import org.mockito.kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ViewExtTest {
    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var motionEvent: MotionEvent
    @Mock
    private lateinit var onClickAction: () -> Unit
    @Mock
    private lateinit var onTouchAction: (MotionEvent) -> Unit


    @Test
    fun `Should provide a shortcut to access the halved width of a view`() {
        // GIVEN
        whenever(view.width).thenReturn(300)

        // THEN
        assertThat(view.halfWidth).isEqualTo(150)
    }

    @Test
    fun `Should provide a shortcut to access the halved height of a view`() {
        // GIVEN
        whenever(view.height).thenReturn(300)

        // THEN
        assertThat(view.halfHeight).isEqualTo(150)
    }

    @Test
    fun `Should provide a shortcut to access the size of a view`() {
        // GIVEN
        whenever(view.width).thenReturn(250)
        whenever(view.height).thenReturn(500)

        // THEN
        assertThat(view.size).isEqualTo(Size(250, 500))
    }

    @Test
    fun `Should provide a shortcut to access the application component`() {
        // GIVEN
        val applicationComponent: ApplicationComponent = mock()

        val application: Eqs = mock {
            on { it.applicationComponent } doReturn applicationComponent
        }

        val context: Context = mock {
            on { it.applicationContext } doReturn application
        }

        whenever(view.context).thenReturn(context)

        // THEN
        assertThat(view.applicationComponent).isEqualTo(applicationComponent)
    }

    @Test
    fun `Should provide a typed shortcut to access the layout params of a view`() {
        // GIVEN
        val layoutParams = LayoutParamsA()

        whenever(view.layoutParams).thenReturn(layoutParams)

        // THEN
        assertThat(view.typedLayoutParams<LayoutParamsA>()).isEqualTo(layoutParams)
    }

    @Test(expected = Exception::class)
    fun `Should throw an exception when the layout params don't have the desired type`() {
        // GIVEN
        val layoutParams = LayoutParamsA()

        whenever(view.layoutParams).thenReturn(layoutParams)

        // WHEN
        view.typedLayoutParams<LayoutParamsB>()
    }

    @Test
    fun `Should provide a shortcut to show a view`() {
        // WHEN
        view.show()

        // THEN
        verify(view).visibility = VISIBLE
    }

    @Test
    fun `Should provide a shortcut to hide a view`() {
        // WHEN
        view.hide()

        // THEN
        verify(view).visibility = GONE
    }

    @Test
    fun `Should toggle a view's visibility to visible instead of gone`() {
        // WHEN
        view.toggleVisibleGone(visible = true)

        // THEN
        verify(view).visibility = VISIBLE
    }

    @Test
    fun `Should toggle a view's visibility to gone instead of visible`() {
        // WHEN
        view.toggleVisibleGone(visible = false)

        // THEN
        verify(view).visibility = GONE
    }

    @Test
    fun `Should toggle a view's visibility to visible instead of invisible`() {
        // WHEN
        view.toggleVisibleInvisible(visible = true)

        // THEN
        verify(view).visibility = VISIBLE
    }

    @Test
    fun `Should toggle a view's visibility to invisible instead of visible`() {
        // WHEN
        view.toggleVisibleInvisible(visible = false)

        // THEN
        verify(view).visibility = INVISIBLE
    }

    @Test
    fun `Should toggle a view's transparency to transparent instead of opaque`() {
        // WHEN
        view.toggleTransparentOpaque(transparent = true)

        // THEN
        verify(view).alpha = 0.5f
    }

    @Test
    fun `Should toggle a view's transparency to opaque instead of transparent`() {
        // WHEN
        view.toggleTransparentOpaque(transparent = false)

        // THEN
        verify(view).alpha = 1.0f
    }

    @Test
    fun `Should call the associated action when a view is clicked`() {
        // GIVEN
        view.onClick(onClickAction)

        // WHEN
        clickView()

        // THEN
        verify(onClickAction).invoke()
    }

    @Test
    fun `Should call the associated action when a view is touched`() {
        // GIVEN
        view.onTouch(onTouchAction)

        // WHEN
        touchView()

        // THEN
        verify(onTouchAction).invoke(motionEvent)
    }

    @Test
    fun `Should consume the touch when its associated action is called`() {
        // GIVEN
        view.onTouch(onTouchAction)

        // WHEN
        val touchConsumed = touchView()

        // THEN
        assertThat(touchConsumed).isTrue()
    }

    @Test
    fun `Should provide a shortcut to retrieve a string`() {
        // GIVEN
        val resources: Resources = mock {
            on { getString(1) } doReturn "a"
        }

        whenever(view.resources) doReturn resources

        // THEN
        assertThat(view.getString(1)).isEqualTo("a")
    }

    private fun clickView() {
        argumentCaptor<OnClickListener> {
            verify(view).setOnClickListener(capture())

            lastValue.onClick(view)
        }
    }

    private fun touchView(): Boolean {
        var touchConsumed = false

        argumentCaptor<OnTouchListener> {
            verify(view).setOnTouchListener(capture())

            touchConsumed = lastValue.onTouch(view, motionEvent)
        }

        return touchConsumed
    }

    private class LayoutParamsA : LayoutParams(0, 0)

    private class LayoutParamsB : LayoutParams(0, 0)
}