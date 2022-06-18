package hello.advanced.config.v2_dynamicproxy.handler

import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
    private val target: Any,
    private val trace: LogTrace,
    private val patterns: Array<String>
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {

        // Method 이름 필터
        val methodName = method.name
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return invoke(args, method)
        }

        val template = TraceTemplate(trace)
        var result: Any? = null
        val message = "${method.declaringClass.simpleName}.${method.name}"
        template.execute(message) {
            result = invoke(args, method)
        }
        return result
    }

    private fun invoke(args: Array<out Any>?, method: Method) =
        if (args == null) method.invoke(target) else method.invoke(target, *args)
}
