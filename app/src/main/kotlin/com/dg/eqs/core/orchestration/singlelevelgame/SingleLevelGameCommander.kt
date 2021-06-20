package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.orchestration.GameCommander


class SingleLevelGameCommander(
        singleLevelGameLevelRepository: SingleLevelGameLevelRepository,
        singleLevelGameInfoRepository: SingleLevelGameInfoRepository,
        singleLevelGameStatusRepository: SingleLevelGameStatusRepository,
        intentionExecutor: IntentionExecutor)
    : GameCommander(
        singleLevelGameLevelRepository,
        singleLevelGameInfoRepository,
        singleLevelGameStatusRepository,
        intentionExecutor)