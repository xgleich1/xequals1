package com.dg.eqs.base

import com.dg.eqs.base.Panel.PanelParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PanelParamsTest {
    @Test
    fun a_panel_params_center_x_coordinate_is_its_horizontal_center() {
        // GIVEN
        val panelParams = PanelParams(
                width = 100,
                height = 0,
                firstX = 30,
                firstY = 0)

        // THEN
        assertThat(panelParams.centerX).isEqualTo(80)
    }

    @Test
    fun a_panel_params_always_provides_its_most_recent_center_x_coordinate() {
        // GIVEN
        val panelParams = PanelParams(
                width = 100,
                height = 0,
                firstX = 30,
                firstY = 0)

        panelParams.width = 80
        panelParams.firstX = 20

        // THEN
        assertThat(panelParams.centerX).isEqualTo(60)
    }
}