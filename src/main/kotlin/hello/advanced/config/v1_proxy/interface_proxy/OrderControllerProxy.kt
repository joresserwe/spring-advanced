package hello.advanced.config.v1_proxy.interface_proxy

import hello.advanced.app.v1.OrderControllerV1
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace

class OrderControllerProxy(
    private val target: OrderControllerV1,
    private val trace: LogTrace
) : OrderControllerV1 {

    override fun request(itemId: String): String {
        lateinit var result: String
        val template = TraceTemplate(trace)
        template.execute("OrderController.request()") {
            result = target.request(itemId)
        }
        return result
    }

    override fun noLog(): String {
        return target.noLog()
    }
}
