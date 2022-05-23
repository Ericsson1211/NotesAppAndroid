package sk.globing.common

import sk.globing.common.extensions.ReturnResult

abstract class UseCaseReturningResultWithNoParams<out T : Any> : UseCase<Unit, ReturnResult<T>>() {

    suspend operator fun invoke() = super.invoke(Unit)
}