package hello.advanced.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class AspectV2 {

    // hello.advanced.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.advanced.order..*(..))")
    private fun allOrder() {
    }

    @Around("allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info { "[log] ${joinPoint.signature}" }
        return joinPoint.proceed()
    }

    private val log = KotlinLogging.logger {}
}
