package hello.advanced.config.v1_proxy.concrete_proxy

import hello.advanced.app.v2.OrderRepositoryV2
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy(
    private val trace: LogTrace
) : OrderRepositoryV2() {

    override fun save(itemId: String) {
        val template = TraceTemplate(trace)
        template.execute("OrderRepository.save()") {
            super.save(itemId)
        }
    }
}
