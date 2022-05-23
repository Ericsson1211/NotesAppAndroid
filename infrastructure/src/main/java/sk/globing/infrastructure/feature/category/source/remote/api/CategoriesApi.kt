package sk.globing.infrastructure.feature.category.source.remote.api

import retrofit2.http.GET
import sk.globing.domain.feature.category.model.Categories

interface CategoriesApi {
    @GET("main/categories.json")
    suspend fun getCategories(): Categories
}
