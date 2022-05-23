package sk.globing.core.di

import sk.globing.common.di.apiModule

/**
 * Koin module for other layers
 */
val allModules = apiModule + noteModule + categoriesModule
