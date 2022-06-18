package hello.advanced.config.v1_proxy

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
    fun orderControllerProxy(): OrderControllerConcreteProxy {
        return OrderControllerConcreteProxy(orderServiceProxy(), trace())
    }

    @Bean
    fun orderServiceProxy(): OrderServiceConcreteProxy {
        return OrderServiceConcreteProxy(orderRepositoryProxy(), trace())
    }

    @Bean
    fun orderRepositoryProxy(): OrderRepositoryConcreteProxy {
        return OrderRepositoryConcreteProxy(trace())
    }

}
