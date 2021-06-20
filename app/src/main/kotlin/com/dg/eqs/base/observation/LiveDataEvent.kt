package com.dg.eqs.base.observation


class LiveDataEvent<T : Any>(private val content: T) {
    private var exposed = false


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as LiveDataEvent<*>

        if(content != other.content) return false

        return true
    }

    override fun hashCode() = content.hashCode()

    override fun toString() = "LiveDataEvent($content)"

    fun getContentOnce() = if(!exposed) {
        exposed = true

        content
    } else {
        null
    }
}