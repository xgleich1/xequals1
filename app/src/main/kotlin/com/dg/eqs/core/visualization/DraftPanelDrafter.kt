package com.dg.eqs.core.visualization

import com.dg.eqs.core.mapping.OriginToDraftMapper


class DraftPanelDrafter(
        originToDraftMapper: OriginToDraftMapper,
        draftPanelPencil: DraftPanelPencil)
    : Drafter(
        originToDraftMapper,
        draftPanelPencil)