package sk.globing.domain.feature.category.repository

import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.category.model.Categories

interface CategoriesRepository {
    suspend fun getCategories(): ReturnResult<Categories>
}
