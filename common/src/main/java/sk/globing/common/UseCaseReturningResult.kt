package sk.globing.common

import sk.globing.common.extensions.ReturnResult

abstract class UseCaseReturningResult<in Params, out T : Any> : UseCase<Params, ReturnResult<T>>()