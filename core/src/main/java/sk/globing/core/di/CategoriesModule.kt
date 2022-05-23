package sk.globing.core.di

import org.koin.dsl.module
import sk.globing.common.di.provideRetrofit
import sk.globing.data.feature.categories.repository.CategoriesRepositoryImpl
import sk.globing.data.feature.categories.source.CategoriesRemoteSource
import sk.globing.domain.feature.category.repository.CategoriesRepository
import sk.globing.domain.feature.category.usecase.GetCategoriesUseCase
import sk.globing.infrastructure.feature.category.source.remote.api.CategoriesApi
import sk.globing.infrastructure.feature.category.source.remote.source.CategoriesRemoteSourceImpl

val categoriesModule = module {

    /**
     * Remote Source references
     */
    single {
        provideRetrofit().create(CategoriesApi::class.java)
    }

    single<CategoriesRepository> { CategoriesRepositoryImpl(get()) }

    single<CategoriesRemoteSource> { CategoriesRemoteSourceImpl(get()) }

    factory { GetCategoriesUseCase(get()) }
}
