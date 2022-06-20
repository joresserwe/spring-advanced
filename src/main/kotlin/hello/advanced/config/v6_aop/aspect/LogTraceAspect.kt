package hello.advanced.config.v6_aop.aspect

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class LogTraceAspect(
    private val trace: LogTrace
) {

    @Around("execution(* hello.advanced.app..*(..))") // Advice
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        val template = TraceTemplate(trace)
        var result: Any? = null
        template.execute(joinPoint.signature.toShortString()) {
            result = joinPoint.proceed()
        }
        return result
    }
}
