package hello.advanced.config.v2_dynamicproxy.handler

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val trace: LogTrace
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
        val template = TraceTemplate(trace)
        var result: Any? = null
        val message = "${method.declaringClass.simpleName}.${method.name}"
        template.execute(message) {
            result = if (args == null) method.invoke(target) else method.invoke(target, *args)
        }
        return result
    }
}
