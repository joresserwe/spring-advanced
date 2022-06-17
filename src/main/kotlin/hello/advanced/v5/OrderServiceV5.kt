package hello.advanced.v5

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val orderRepository: OrderRepositoryV5,
    trace: LogTrace
) {
    private val traceTemplate: TraceTemplate

    init {
        this.traceTemplate = TraceTemplate(trace)
    }

    fun orderItem(itemId: String) {
        traceTemplate.execute("OrderService.orderItem()") {
            orderRepository.save(itemId)
        }
    }
}
