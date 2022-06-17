package hello.advanced.trace.callback

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

class TraceTemplate(
    private val trace: LogTrace
) {
    fun <T> execute(message: String, callback: TraceCallback<T>): T {
        lateinit var status: TraceStatus

        try {
            status = trace.begin(message)
            val result = callback.call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
