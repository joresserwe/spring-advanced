package hello.advanced.config.v1_proxy

import hello.advanced.app.v1.*
import hello.advanced.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import hello.advanced.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import hello.advanced.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class InterfaceProxyConfig {

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun orderController(): OrderControllerV1 {
        val orderControllerV1Impl = OrderControllerV1Impl(orderService())
        return OrderControllerInterfaceProxy(orderControllerV1Impl, trace())
    }

    @Bean
    fun orderService(): OrderServiceV1 {
        val orderServiceV1Impl = OrderServiceV1Impl(orderRepository())
        return OrderServiceInterfaceProxy(orderServiceV1Impl, trace())
    }

    @Bean
    fun orderRepository(): OrderRepositoryV1 {
        val orderRepositoryV1Impl = OrderRepositoryV1Impl()
        return OrderRepositoryInterfaceProxy(orderRepositoryV1Impl, trace())
    }
}
