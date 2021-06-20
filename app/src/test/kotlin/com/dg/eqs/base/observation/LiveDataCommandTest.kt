package com.dg.eqs.base.observation

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LiveDataCommandTest {
    @Test
    fun `A live data command executes its command only once`() {
        // GIVEN
        var commandExecutionCounter = 0

        val liveDataCommand = LiveDataCommand()

        // WHEN
        liveDataCommand.execute { ++commandExecutionCounter }
        liveDataCommand.execute { ++commandExecutionCounter }

        // THEN
        assertThat(commandExecutionCounter).isEqualTo(1)
    }
}