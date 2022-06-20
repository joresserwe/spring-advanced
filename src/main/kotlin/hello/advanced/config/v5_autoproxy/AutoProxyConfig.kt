package hello.advanced.config.v5_autoproxy

import hello.advanced.config.AppV1Config
import hello.advanced.config.AppV2Config
import hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AutoProxyConfig {

    @Bean
    fun trace() = ThreadLocalLogTrace()

    //@Bean
    fun advisor1(trace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(trace()))
    }

    //@Bean
    fun advisor2(trace: LogTrace): Advisor {
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "execution(* hello.advanced.app..*(..))"
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(trace()))
    }

    @Bean
    fun advisor3(trace: LogTrace): Advisor {
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "execution(* hello.advanced.app..*(..)) && !execution(* hello.advanced.app..noLog(..))"
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(trace()))
    }

}
