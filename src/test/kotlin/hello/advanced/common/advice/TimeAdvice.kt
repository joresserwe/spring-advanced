package hello.advanced.common.advice

import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class TimeAdvice : MethodInterceptor {

    private val log = KotlinLogging.logger {}

    override fun invoke(invocation: MethodInvocation): Any? {

        log.info { "TimeProxy 실행" }
        val startTime = System.currentTimeMillis()

        val result = invocation.proceed()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "TimeProxy 종료 resultTime=$resultTime" }
        return result
    }
}
