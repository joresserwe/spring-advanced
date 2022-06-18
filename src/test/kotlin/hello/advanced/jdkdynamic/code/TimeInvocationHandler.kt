package hello.advanced.jdkdynamic.code

import mu.KotlinLogging
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
    private val target: Any
) : InvocationHandler {
    private val log = KotlinLogging.logger {}
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        log.info { "TimeProxy 실행" }
        val startTime = System.currentTimeMillis()
        val result = if (args != null) method?.invoke(target, *args) else method?.invoke(target)
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "TimeProxy 종료 resultTime=$resultTime" }
        return result
    }


}
