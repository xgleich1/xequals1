package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.orchestration.GameCommander


class EndlessLevelGameCommander(
        endlessLevelGameLevelRepository: EndlessLevelGameLevelRepository,
        endlessLevelGameInfoRepository: EndlessLevelGameInfoRepository,
        endlessLevelGameStatusRepository: EndlessLevelGameStatusRepository,
        intentionExecutor: IntentionExecutor)
    : GameCommander(
        endlessLevelGameLevelRepository,
        endlessLevelGameInfoRepository,
        endlessLevelGameStatusRepository,
        intentionExecutor)