package hello.advanced.v5

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.v4.OrderServiceV4
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV5(
    private val orderService: OrderServiceV4,
    trace: LogTrace
) {
    private val traceTemplate: TraceTemplate

    init {
        this.traceTemplate = TraceTemplate(trace)
    }

    @GetMapping("/v5/request")
    fun request(@RequestParam itemId: String): String {
        traceTemplate.execute("OrderController.request()") {
            orderService.orderItem(itemId)
        }
        return "ok"
    }
}
