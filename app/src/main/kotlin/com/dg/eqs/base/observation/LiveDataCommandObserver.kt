package com.dg.eqs.base.observation

import androidx.lifecycle.Observer


class LiveDataCommandObserver(
        private val command: () -> Unit) : Observer<LiveDataCommand> {

    override fun onChanged(liveDataCommand: LiveDataCommand) {
        liveDataCommand.execute(command)
    }
}