package com.chs.yourbudget.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DomainModule::class])
@ComponentScan("com.chs.yourbudget.presentation")
class PresentationModule