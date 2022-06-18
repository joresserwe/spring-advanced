package hello.advanced.config.v1_proxy.concrete_proxy

import hello.advanced.app.v2.OrderControllerV2
import hello.advanced.app.v2.OrderServiceV2
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace

class OrderControllerConcreteProxy(
    orderService: OrderServiceV2,
    private val trace: LogTrace
) : OrderControllerV2(orderService) {

    override fun request(itemId: String): String {
        val template = TraceTemplate(trace)
        lateinit var result: String
        template.execute("OrderController.request()") {
            result = super.request(itemId)
        }
        return result

    }
}
