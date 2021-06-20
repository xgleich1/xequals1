package com.dg.eqs.base.observation


class LiveDataCommand {
    private var executed = false


    fun execute(command: () -> Unit) {
        if(!executed) {
            executed = true

            command()
        }
    }
}