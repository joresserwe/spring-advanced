package hello.advanced.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV1(
    private val orderService: OrderServiceV1,
    private val helloTraceV1: HelloTraceV1
) {

    @GetMapping("/v1/request")
    fun request(@RequestParam itemId: String): String {

        lateinit var status: TraceStatus
        try {
            status = helloTraceV1.begin("OrderController.request()")
            orderService.orderItem(itemId)
            helloTraceV1.end(status)
            return "ok"
        } catch (e: Exception) {
            helloTraceV1.exception(status, e)
            throw e
        }
    }
}
