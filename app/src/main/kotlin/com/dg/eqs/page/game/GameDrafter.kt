package com.dg.eqs.page.game

import com.dg.eqs.core.mapping.OriginToDraftMapper
import com.dg.eqs.core.visualization.Drafter


class GameDrafter(
        originToDraftMapper: OriginToDraftMapper,
        gamePencil: GamePencil)
    : Drafter(
        originToDraftMapper,
        gamePencil)