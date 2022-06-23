package hello.advanced.order.aop.exam.aop

import hello.advanced.order.aop.exam.annotation.Retry
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class RetryAspect {

    private val log = KotlinLogging.logger {}

    @Around("@annotation(retry)")
    fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any? {

        lateinit var exceptionHolder: Exception
        log.info { "[retry] ${joinPoint.signature} retry=$retry" }
        repeat(retry.value) { times ->
            try {
                log.info { "[retry] try count=${times}/${retry.value}" }
                return joinPoint.proceed()
            } catch (e: Exception) {
                exceptionHolder = e
            }
        }
        throw exceptionHolder
    }
}
