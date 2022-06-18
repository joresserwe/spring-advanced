package hello.advanced.config.v1_proxy

import hello.advanced.app.v2.OrderControllerV2
import hello.advanced.app.v2.OrderRepositoryV2
import hello.advanced.app.v2.OrderServiceV2
import hello.advanced.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import hello.advanced.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import hello.advanced.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ConcreteProxyConfig {

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(): OrderControllerV2 {
        return OrderControllerConcreteProxy(orderService(), trace())
    }

    @Bean
    fun orderService(): OrderServiceV2 {
        return OrderServiceConcreteProxy(orderRepository(), trace())
    }

    @Bean
    fun orderRepository(): OrderRepositoryV2 {
        return OrderRepositoryConcreteProxy(trace())
    }

}
