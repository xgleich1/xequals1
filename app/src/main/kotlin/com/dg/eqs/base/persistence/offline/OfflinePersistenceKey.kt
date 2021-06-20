package com.dg.eqs.base.persistence.offline


abstract class OfflinePersistenceKey(val rawKey: String) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as OfflinePersistenceKey

        if(rawKey != other.rawKey) return false

        return true
    }

    override fun hashCode() = rawKey.hashCode()

    override fun toString() = "OfflinePersistenceKey($rawKey)"
}