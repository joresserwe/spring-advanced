package hello.advanced

import hello.advanced.order.OrderRepository
import hello.advanced.order.OrderService
import hello.advanced.order.aop.AspectV6Advice
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
//@Import(AspectV1::class)
//@Import(AspectV2::class)
//@Import(AspectV3::class)
//@Import(AspectV4Pointcut::class)
//@Import(AspectV5Order.LogAspect::class, AspectV5Order.TxAspect::class)
@Import(AspectV6Advice::class)
class AopTest : FunSpec() {
    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    init {
        this.context("aopTest") {
            test("aopInfo") {
                log.info { "isAopProxy, orderService=${AopUtils.isAopProxy(orderService)}" }
                log.info { "isAopProxy, orderRepository=${AopUtils.isAopProxy(orderRepository)}" }
            }
            test("success") {
                orderService.orderItem("itemA")
            }
            test("exception") {
                shouldThrow<IllegalStateException> { orderService.orderItem("ex") }
            }
        }
    }
}
