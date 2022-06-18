package hello.advanced.config.v1_proxy.interface_proxy

import hello.advanced.app.v1.OrderServiceV1
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace

class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val trace: LogTrace
) : OrderServiceV1 {

    override fun save(itemId: String) {
        val template = TraceTemplate(trace)
        template.execute("OrderService.save()") {
            target.save(itemId)
        }
    }
}
