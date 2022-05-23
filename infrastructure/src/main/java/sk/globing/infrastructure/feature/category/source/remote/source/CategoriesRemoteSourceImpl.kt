package sk.globing.infrastructure.feature.category.source.remote.source

import sk.globing.common.extensions.ReturnResult
import sk.globing.common.extensions.safeApiCall
import sk.globing.data.feature.categories.source.CategoriesRemoteSource
import sk.globing.domain.feature.category.model.Categories
import sk.globing.infrastructure.feature.category.source.remote.api.CategoriesApi

class CategoriesRemoteSourceImpl(private val categoriesApi: CategoriesApi) : CategoriesRemoteSource {

    override suspend fun getCategories(): ReturnResult<Categories> =
        safeApiCall(
            call = {
                categoriesApi.getCategories().let {
                    ReturnResult.Success(it)
                }
            },
            errorMessage = "Cannot download categories json. Try again later"
        )
}
