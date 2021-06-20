package com.dg.eqs.base.injection.module

import com.dg.eqs.core.mapping.OriginToDraftMapper
import com.dg.eqs.core.visualization.DraftPanelDrafter
import com.dg.eqs.core.visualization.DraftPanelPencil
import dagger.Module
import dagger.Provides


@Module
open class DraftPanelModule(
        private val draftPanelPencil: DraftPanelPencil) {

    @Provides
    open fun provideDraftPanelDrafter(
            originToDraftMapper: OriginToDraftMapper,
            draftPanelPencil: DraftPanelPencil
    ): DraftPanelDrafter {

        return DraftPanelDrafter(
                originToDraftMapper,
                draftPanelPencil)
    }

    @Provides
    open fun provideDraftPanelPencil(): DraftPanelPencil {
        return draftPanelPencil
    }
}