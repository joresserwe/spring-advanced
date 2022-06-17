package hello.advanced.trace.callback

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class TraceTemplate(
    private val trace: LogTrace
) {
    private val log = KotlinLogging.logger {}

    fun execute(message: String, callback: TraceCallback) {
        lateinit var status: TraceStatus
        try {
            status = trace.begin(message)
            callback.call()
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
