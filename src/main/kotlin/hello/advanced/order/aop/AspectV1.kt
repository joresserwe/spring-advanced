package hello.advanced.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class AspectV1 {

    private val log = KotlinLogging.logger {}

    @Around("execution(* hello.advanced.order..*(..))")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info { "[log] ${joinPoint.signature}" }
        return joinPoint.proceed()
    }
}
