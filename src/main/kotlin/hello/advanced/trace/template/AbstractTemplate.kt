package hello.advanced.trace.template

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

abstract class AbstractTemplate(
    private val trace: LogTrace
) {

    fun execute(message: String) {
        lateinit var status: TraceStatus
        try {
            status = trace.begin(message)
            call()
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    abstract fun call()

}
