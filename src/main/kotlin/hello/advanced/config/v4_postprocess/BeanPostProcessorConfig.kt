package hello.advanced.config.v4_postprocess

import hello.advanced.config.AppV1Config
import hello.advanced.config.AppV2Config
import hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.config.v4_postprocess.postprocess.PackageLogTracePostProcessor
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class BeanPostProcessorConfig {

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun logTracePostProcessor() = PackageLogTracePostProcessor("hello.advanced.app", getAdvisor(trace()))

    private fun getAdvisor(trace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(trace()))
    }
}
