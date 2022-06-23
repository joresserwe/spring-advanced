package hello.advanced.internalcall.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class CallLogAspect {

    private val log = KotlinLogging.logger {}

    @Before("execution(* hello..internalcall..*(..))")
    fun doLog(joinPoint: JoinPoint) {
        log.info("aop=${joinPoint.signature}")
    }
}
