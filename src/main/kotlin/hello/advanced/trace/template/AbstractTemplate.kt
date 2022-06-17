package hello.advanced.trace.template

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace


abstract class AbstractTemplate<T>(
    private val trace: LogTrace
) {

    fun execute(message: String): T {
        lateinit var status: TraceStatus
        try {
            status = trace.begin(message)
            val result = call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    abstract fun call(): T
}
