package hello.advanced.pointcut

import hello.advanced.order.OrderService
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Import(BeanTest.BeanAspect::class)
@SpringBootTest
class BeanTest {

    @Autowired
    private lateinit var orderService: OrderService

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Aspect
    @Component
    class BeanAspect {
        private val log = KotlinLogging.logger {}

        @Around("bean(orderService) || bean(*Repository)")
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[bean] ${joinPoint.signature}")
            return joinPoint.proceed()
        }
    }
}
