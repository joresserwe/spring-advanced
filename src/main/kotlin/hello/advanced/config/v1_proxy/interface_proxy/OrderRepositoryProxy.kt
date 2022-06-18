package hello.advanced.config.v1_proxy.interface_proxy

import hello.advanced.app.v1.OrderRepositoryV1
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace

class OrderRepositoryProxy(
    private val target: OrderRepositoryV1,
    private val trace: LogTrace
) : OrderRepositoryV1 {

    override fun save(itemId: String) {
        val template = TraceTemplate(trace)
        template.execute("orderRepository.save()") {
            target.save(itemId)
        }
    }
}
