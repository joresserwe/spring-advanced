package hello.advanced.config.v1_proxy

import hello.advanced.app.v1.OrderControllerV1Impl
import hello.advanced.app.v1.OrderRepositoryV1Impl
import hello.advanced.app.v1.OrderServiceV1Impl
import hello.advanced.config.v1_proxy.interface_proxy.OrderControllerProxy
import hello.advanced.config.v1_proxy.interface_proxy.OrderRepositoryProxy
import hello.advanced.config.v1_proxy.interface_proxy.OrderServiceProxy
import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class InterfaceProxyConfig {

    @Bean
    fun trace() = ThreadLocalLogTrace()

    @Bean
    fun orderControllerProxy(): OrderControllerProxy {
        val orderControllerV1Impl = OrderControllerV1Impl(orderServiceProxy())
        return OrderControllerProxy(orderControllerV1Impl, trace())
    }
    
    @Bean
    fun orderServiceProxy(): OrderServiceProxy {
        val orderServiceV1Impl = OrderServiceV1Impl(orderRepositoryProxy())
        return OrderServiceProxy(orderServiceV1Impl, trace())
    }

    @Bean
    fun orderRepositoryProxy(): OrderRepositoryProxy {
        val orderRepositoryV1Impl = OrderRepositoryV1Impl()
        return OrderRepositoryProxy(orderRepositoryV1Impl, trace())
    }

}
