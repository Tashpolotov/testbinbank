package kg.aza.testbinbank.core

import org.koin.core.module.Module

abstract class KoinModule {
    abstract fun create(): Module
}