package sk.globing.data.feature.categories.repository

import sk.globing.data.feature.categories.source.CategoriesRemoteSource
import sk.globing.domain.feature.category.repository.CategoriesRepository

class CategoriesRepositoryImpl(private val categoriesRemoteSource: CategoriesRemoteSource) : CategoriesRepository {

    override suspend fun getCategories() = categoriesRemoteSource.getCategories()
}
