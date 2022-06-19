package hello.advanced.config.v3_proxyfactory.advice

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogTraceAdvice(
    private val trace: LogTrace
) : MethodInterceptor {

    override fun invoke(invocation: MethodInvocation): Any? {
        val template = TraceTemplate(trace)
        var result: Any? = null
        val method = invocation.method
        val message = "${method.declaringClass.simpleName}.${method.name}"
        template.execute(message) {
            result = invocation.proceed()
        }
        return result
    }
}
