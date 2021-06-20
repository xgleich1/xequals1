package com.dg.eqs.page.game

import android.os.Parcelable
import com.dg.eqs.core.progression.LevelKey
import kotlinx.android.parcel.Parcelize


sealed class GameConfig : Parcelable {
    abstract val showRevertButton: Boolean
    abstract val showInvertButton: Boolean
    abstract val showSkipButton: Boolean


    @Parcelize
    object EndlessLevelGameConfig : GameConfig() {
        override val showRevertButton = true
        override val showInvertButton = true
        override val showSkipButton = true
    }

    @Parcelize
    class SingleLevelGameConfig(
            val levelKey: LevelKey) : GameConfig() {

        override val showRevertButton = true
        override val showInvertButton = true
        override val showSkipButton = false


        override fun equals(other: Any?): Boolean {
            if(this === other) return true
            if(javaClass != other?.javaClass) return false
            if(!super.equals(other)) return false

            other as SingleLevelGameConfig

            if(levelKey != other.levelKey) return false

            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()

            result = 31 * result + levelKey.hashCode()

            return result
        }
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as GameConfig

        if(showRevertButton != other.showRevertButton) return false
        if(showInvertButton != other.showInvertButton) return false
        if(showSkipButton != other.showSkipButton) return false

        return true
    }

    override fun hashCode(): Int {
        var result = showRevertButton.hashCode()

        result = 31 * result + showInvertButton.hashCode()
        result = 31 * result + showSkipButton.hashCode()

        return result
    }
}