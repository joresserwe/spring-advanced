package hello.advanced.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2(
    private val helloTraceV2: HelloTraceV2
) {
    fun save(traceId: TraceId, itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = helloTraceV2.beginSync(traceId, "OrderRepository.save()")
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!!!")
            }
            sleep(5_000)
            helloTraceV2.end(status)
        } catch (e: Exception) {
            helloTraceV2.exception(status, e)
            throw e
        }
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
