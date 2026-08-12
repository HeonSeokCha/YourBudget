package com.chs.yourbudget.di

import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication

@KoinApplication(
    modules = [
        DomainModule::class,
        DataModule::class,
        PresentationModule::class,
        PlatformModule::class
    ]
)
@Configuration
class KoinModule