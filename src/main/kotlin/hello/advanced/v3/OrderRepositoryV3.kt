package hello.advanced.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3(
    private val trace: LogTrace,
) {
    fun save(itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = trace.begin("OrderRepository.save()")
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!!!")
            }
            sleep(1_000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}