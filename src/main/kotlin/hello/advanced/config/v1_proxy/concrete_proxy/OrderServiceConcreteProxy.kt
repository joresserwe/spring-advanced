package hello.advanced.config.v1_proxy.concrete_proxy

import hello.advanced.app.v2.OrderRepositoryV2
import hello.advanced.app.v2.OrderServiceV2
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace

class OrderServiceConcreteProxy(
    orderRepository: OrderRepositoryV2,
    private val trace: LogTrace
) : OrderServiceV2(orderRepository) {

    override fun save(itemId: String) {
        val template = TraceTemplate(trace)
        template.execute("OrderService.save()") {
            super.save(itemId)
        }
    }
}
