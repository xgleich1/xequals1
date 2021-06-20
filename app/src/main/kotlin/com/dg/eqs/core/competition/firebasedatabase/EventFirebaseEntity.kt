package com.dg.eqs.core.competition.firebasedatabase

import kotlin.properties.Delegates.notNull


class EventFirebaseEntity {
    var number: Int by notNull()
    var exercise: String by notNull()
    var scoreBoardKey: String by notNull()
}