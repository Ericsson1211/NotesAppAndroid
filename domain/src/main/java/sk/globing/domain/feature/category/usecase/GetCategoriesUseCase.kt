package sk.globing.domain.feature.category.usecase

import sk.globing.common.UseCaseReturningResultWithNoParams
import sk.globing.domain.feature.category.model.Categories
import sk.globing.domain.feature.category.repository.CategoriesRepository

class GetCategoriesUseCase(private val categoriesRepository: CategoriesRepository) : UseCaseReturningResultWithNoParams<Categories>() {

    override suspend fun doWork(params: Unit) = categoriesRepository.getCategories()
}
