package hello.advanced.config.v3_proxyfactory

import hello.advanced.app.v1.*
import hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import mu.KotlinLogging
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV1 {

    private val log = KotlinLogging.logger {}

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(): OrderControllerV1 {
        val target = OrderControllerV1Impl(orderService())
        val proxyFactory = ProxyFactory(target)
        val proxy = addAdvisor(proxyFactory).proxy as OrderControllerV1
        log.info("ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}")
        return proxy
    }

    @Bean
    fun orderService(): OrderServiceV1 {
        val target = OrderServiceV1Impl(orderRepository())
        val proxyFactory = ProxyFactory(target)
        val proxy = addAdvisor(proxyFactory).proxy as OrderServiceV1
        log.info("ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}")
        return proxy
    }

    @Bean
    fun orderRepository(): OrderRepositoryV1 {
        val target = OrderRepositoryV1Impl()
        val proxyFactory = ProxyFactory(target)
        val proxy = addAdvisor(proxyFactory).proxy as OrderRepositoryV1
        log.info("ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}")
        return proxy
    }

    private fun addAdvisor(proxyFactory: ProxyFactory): ProxyFactory {
        // pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        val advisor = DefaultPointcutAdvisor(pointcut, LogTraceAdvice(trace()))
        proxyFactory.addAdvisor(advisor)
        return proxyFactory
    }
}
