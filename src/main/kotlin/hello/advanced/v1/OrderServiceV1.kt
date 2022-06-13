package hello.advanced.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Service

@Service
class OrderServiceV1(
    private val orderRepository: OrderRepositoryV1,
    private val helloTraceV1: HelloTraceV1
) {
    fun orderItem(itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = helloTraceV1.begin("OrderService.orderItem()")
            orderRepository.save(itemId)
            helloTraceV1.end(status)
        } catch (e: Exception) {
            helloTraceV1.exception(status, e)
            throw e
        }
    }
}
