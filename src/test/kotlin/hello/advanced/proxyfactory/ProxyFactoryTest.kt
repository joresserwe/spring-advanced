package hello.advanced.proxyfactory

import hello.advanced.common.advice.TimeAdvice
import hello.advanced.common.service.ConcreteService
import hello.advanced.common.service.ServiceInterface
import hello.advanced.common.service.ServiceInterfaceImpl
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.AopUtils

class ProxyFactoryTest {

    private val log = KotlinLogging.logger {}

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    fun interfaceProxy() {
        val target = ServiceInterfaceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ServiceInterface
        log.info("targetClass=${target::class.java}")
        log.info("proxyClass=${proxy::class.java}") // proxyClass=class jdk.proxy3.$Proxy16
        proxy.save()

        // Proxy Factory를 통해서 만들어 진것만 AopUtils 사용 가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse
    }

    @Test
    @DisplayName("구체 클래스만 있으면 JDK 동적 프록시 사용")
    fun concreteProxy() {
        val target = ConcreteService()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ConcreteService
        log.info("targetClass=${target::class.java}")
        log.info("proxyClass=${proxy::class.java}") // proxyClass=class hello.advanced.common.service.ConcreteService$$EnhancerBySpringCGLIB$$9c3bc41b
        proxy.call()

        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }

    @Test
    @DisplayName("ProxyTargetClass를 사용하면 Interface가 있어도 CGLIB를 사용하고, Class 기반 Proxy 사용")
    fun proxyTargetClass() {
        val target = ServiceInterfaceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true
        proxyFactory.addAdvice(TimeAdvice())
        val proxy = proxyFactory.proxy as ServiceInterface
        log.info("targetClass=${target::class.java}")
        log.info("proxyClass=${proxy::class.java}") // proxyClass=class hello.advanced.common.service.ServiceInterfaceImpl$$EnhancerBySpringCGLIB$$83aad3f1
        proxy.save()

        // Proxy Factory를 통해서 만들어 진것만 AopUtils 사용 가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }

}
