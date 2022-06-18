package hello.advanced.config.v1_proxy

import hello.advanced.app.v1.OrderControllerV1Impl
import hello.advanced.app.v1.OrderRepositoryV1Impl
import hello.advanced.app.v1.OrderServiceV1Impl
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
    fun orderControllerProxy(): OrderControllerInterfaceProxy {
        val orderControllerV1Impl = OrderControllerV1Impl(orderServiceProxy())
        return OrderControllerInterfaceProxy(orderControllerV1Impl, trace())
    }

    @Bean
    fun orderServiceProxy(): OrderServiceInterfaceProxy {
        val orderServiceV1Impl = OrderServiceV1Impl(orderRepositoryProxy())
        return OrderServiceInterfaceProxy(orderServiceV1Impl, trace())
    }

    @Bean
    fun orderRepositoryProxy(): OrderRepositoryInterfaceProxy {
        val orderRepositoryV1Impl = OrderRepositoryV1Impl()
        return OrderRepositoryInterfaceProxy(orderRepositoryV1Impl, trace())
    }

}
