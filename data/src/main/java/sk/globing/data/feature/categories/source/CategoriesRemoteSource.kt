package sk.globing.data.feature.categories.source

import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.category.model.Categories

interface CategoriesRemoteSource {

    suspend fun getCategories(): ReturnResult<Categories>
}
