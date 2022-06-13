package hello.advanced.v2

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV2(
    private val orderService: OrderServiceV2,
    private val helloTraceV2: HelloTraceV2
) {

    @GetMapping("/v2/request")
    fun request(@RequestParam itemId: String): String {

        lateinit var status: TraceStatus
        try {
            status = helloTraceV2.begin("OrderController.request()")
            orderService.orderItem(status.traceId, itemId)
            helloTraceV2.end(status)
            return "ok"
        } catch (e: Exception) {
            helloTraceV2.exception(status, e)
            throw e
        }
    }
}
