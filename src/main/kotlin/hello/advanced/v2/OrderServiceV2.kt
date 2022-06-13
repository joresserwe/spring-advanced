package hello.advanced.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2,
    private val helloTraceV2: HelloTraceV2
) {
    fun orderItem(traceId: TraceId, itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = helloTraceV2.beginSync(traceId, "OrderService.orderItem()")
            orderRepository.save(status.traceId, itemId)
            helloTraceV2.end(status)
        } catch (e: Exception) {
            helloTraceV2.exception(status, e)
            throw e
        }
    }

}
