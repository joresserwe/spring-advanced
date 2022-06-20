package hello.advanced.config.v6_aop

import hello.advanced.config.AppV1Config
import hello.advanced.config.AppV2Config
import hello.advanced.config.v6_aop.aspect.LogTraceAspect
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AopConfig {

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun logTraceAspect() = LogTraceAspect(trace())
}
