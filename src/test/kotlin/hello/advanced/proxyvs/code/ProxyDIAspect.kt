package hello.advanced.proxyvs.code

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class ProxyDIAspect {
    private val log = KotlinLogging.logger {}

    @Before("execution(* hello.advanced..*(..))")
    fun doTrace(joinPoint: JoinPoint) {
        log.info { "[ProxyDIAspect] ${joinPoint.signature}" }
    }
}
