package com.dg.eqs.base.injection.module

import com.dg.eqs.base.injection.scope.ApplicationScope
import com.dg.eqs.core.mapping.OriginToDraftMapper
import com.dg.eqs.core.mapping.OriginToStringMapper
import com.dg.eqs.core.mapping.StringToOriginMapper
import dagger.Module
import dagger.Provides


@Module
open class MappingModule {
    @Provides
    @ApplicationScope
    open fun provideOriginToDraftMapper(): OriginToDraftMapper {
        return OriginToDraftMapper()
    }

    @Provides
    @ApplicationScope
    open fun provideOriginToStringMapper(): OriginToStringMapper {
        return OriginToStringMapper()
    }

    @Provides
    @ApplicationScope
    open fun provideStringToOriginMapper(): StringToOriginMapper {
        return StringToOriginMapper()
    }
}