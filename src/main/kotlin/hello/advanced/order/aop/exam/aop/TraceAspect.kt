package hello.advanced.order.aop.exam.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class TraceAspect {

    private val log = KotlinLogging.logger {}

    @Before("@annotation(hello.advanced.order.aop.exam.annotation.Trace)")
    fun doTrace(joinPoint: JoinPoint) {
        val args = joinPoint.args
        log.info { "[trace] ${joinPoint.signature} args=$args" }
    }
}
