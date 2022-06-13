package hello.advanced.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository
import kotlin.concurrent.thread

@Repository
class OrderRepositoryV1(
    private val helloTraceV1: HelloTraceV1
) {
    fun save(itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = helloTraceV1.begin("OrderRepository.save()")
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!!!")
            }
            sleep(1000)
            helloTraceV1.end(status)
        } catch (e: Exception) {
            helloTraceV1.exception(status, e)
            throw e
        }
    }

    private fun sleep(millis: Long) {
        thread { Thread.sleep(millis) }
    }
}
