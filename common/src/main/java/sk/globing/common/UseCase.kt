package sk.globing.common

abstract class UseCase<in Params, out T> {

    suspend operator fun invoke(params: Params): T = doWork(params)

    protected abstract suspend fun doWork(params: Params): T
}

abstract class UseCaseNoParams<out T> {

    suspend operator fun invoke(): T = doWork()

    protected abstract suspend fun doWork(): T
}
