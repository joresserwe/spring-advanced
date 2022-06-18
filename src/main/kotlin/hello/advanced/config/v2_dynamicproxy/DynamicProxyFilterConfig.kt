package hello.advanced.config.v2_dynamicproxy

import hello.advanced.app.v1.*
import hello.advanced.config.v2_dynamicproxy.handler.LogTraceFilterHandler
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyFilterConfig {

    companion object {
        private val PATTERNS = arrayOf("request*", "order*", "save*")
    }

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(): OrderControllerV1 {
        val orderController = OrderControllerV1Impl(orderService())
        return Proxy.newProxyInstance(
            OrderControllerV1::class.java.classLoader,
            arrayOf(OrderControllerV1::class.java),
            LogTraceFilterHandler(orderController, trace(), PATTERNS)
        ) as OrderControllerV1
    }

    @Bean
    fun orderService(): OrderServiceV1 {
        val orderService = OrderServiceV1Impl(orderRepository())
        return Proxy.newProxyInstance(
            OrderServiceV1::class.java.classLoader,
            arrayOf(OrderServiceV1::class.java),
            LogTraceFilterHandler(orderService, trace(), PATTERNS)
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepository(): OrderRepositoryV1 {
        val orderRepository = OrderRepositoryV1Impl()
        return Proxy.newProxyInstance(
            OrderRepositoryV1::class.java.classLoader,
            arrayOf(OrderRepositoryV1::class.java),
            LogTraceFilterHandler(orderRepository, trace(), PATTERNS)
        ) as OrderRepositoryV1
    }
}
