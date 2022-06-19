package hello.advanced.config.v3_proxyfactory

import hello.advanced.app.v2.OrderControllerV2
import hello.advanced.app.v2.OrderRepositoryV2
import hello.advanced.app.v2.OrderServiceV2
import hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import mu.KotlinLogging
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV2 {

    private val log = KotlinLogging.logger {}

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(): OrderControllerV2 {
        val target = OrderControllerV2(orderService())
        val proxyFactory = ProxyFactory(target)
        val proxy = addAdvisor(proxyFactory).proxy as OrderControllerV2
        log.info("ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}")
        return proxy
    }

    @Bean
    fun orderService(): OrderServiceV2 {
        val target = OrderServiceV2(orderRepository())
        val proxyFactory = ProxyFactory(target)
        val proxy = addAdvisor(proxyFactory).proxy as OrderServiceV2
        log.info("ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}")
        return proxy
    }

    @Bean
    fun orderRepository(): OrderRepositoryV2 {
        val target = OrderRepositoryV2()
        val proxyFactory = ProxyFactory(target)
        val proxy = addAdvisor(proxyFactory).proxy as OrderRepositoryV2
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
