package hello.advanced.v5

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5(
    trace: LogTrace,
) {
    private val traceTemplate: TraceTemplate

    init {
        this.traceTemplate = TraceTemplate(trace)
    }

    fun save(itemId: String) {
        traceTemplate.execute("OrderController.request()") {
            sleep(1000)
        }
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
