package com.dg.eqs.classes

import com.dg.eqs.core.definition.term.item.Item


fun item(): Item = TestItem()

private class TestItem : Item() {
    override val isNegative get() = TODO("not implemented")


    override fun invert() = TODO("not implemented")

    override fun isConstant() = TODO("not implemented")

    override fun copy() = TODO("not implemented")
}