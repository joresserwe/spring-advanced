package hello.advanced.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV4(
    private val orderService: OrderServiceV4,
    private val trace: LogTrace
) {

    @GetMapping("/v4/request")
    fun request(@RequestParam itemId: String): String {
        object : AbstractTemplate(trace) {
            override fun call() {
                orderService.orderItem(itemId)
            }
        }.execute("OrderController.request()")
        return "ok"
    }


}
