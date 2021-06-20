package com.dg.eqs.base.injection.module

import android.content.res.Resources
import com.dg.eqs.base.concurrency.BackgroundExecutor
import com.dg.eqs.core.interaction.linking.LinkingCommander
import com.dg.eqs.core.interaction.linking.choosing.Chooser
import com.dg.eqs.core.interaction.linking.choosing.widening.SourceWidener
import com.dg.eqs.core.interaction.linking.choosing.widening.TargetWidener
import com.dg.eqs.core.interaction.linking.choosing.widening.WideningDelayer
import com.dg.eqs.core.interaction.linking.choosing.widening.WideningTiming
import com.dg.eqs.core.interaction.scrolling.ScrollingCommander
import dagger.Module
import dagger.Provides


@Module
open class InteractionModule {
    @Provides
    open fun provideLinkingCommander(chooser: Chooser): LinkingCommander {
        return LinkingCommander(chooser)
    }

    @Provides
    open fun provideChooser(sourceWidener: SourceWidener, targetWidener: TargetWidener): Chooser {
        return Chooser(sourceWidener, targetWidener)
    }

    @Provides
    open fun provideSourceWidener(wideningDelayer: WideningDelayer): SourceWidener {
        return SourceWidener(wideningDelayer)
    }

    @Provides
    open fun provideTargetWidener(wideningDelayer: WideningDelayer): TargetWidener {
        return TargetWidener(wideningDelayer)
    }

    @Provides
    open fun provideWideningDelayer(
            backgroundExecutor: BackgroundExecutor,
            wideningTiming: WideningTiming): WideningDelayer {

        return WideningDelayer(
                backgroundExecutor,
                wideningTiming)
    }

    @Provides
    open fun provideWideningTiming(resources: Resources): WideningTiming {
        return WideningTiming(resources)
    }

    @Provides
    open fun provideScrollingCommander(): ScrollingCommander {
        return ScrollingCommander()
    }
}