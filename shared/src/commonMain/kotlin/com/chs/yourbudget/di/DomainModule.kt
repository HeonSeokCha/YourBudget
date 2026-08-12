package com.chs.yourbudget.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DataModule::class])
@ComponentScan("com.chs.yourbudget.domain")
class DomainModule