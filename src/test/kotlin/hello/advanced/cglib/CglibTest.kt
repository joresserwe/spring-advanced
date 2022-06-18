package hello.advanced.cglib

import hello.advanced.cglib.code.TimeMethodInterceptor
import hello.advanced.common.service.ConcreteService
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.cglib.proxy.Enhancer

class CglibTest {

    private val log = KotlinLogging.logger {}

    @Test
    fun cglib() {
        val target = ConcreteService()

        val enhancer = Enhancer()
        enhancer.setSuperclass(ConcreteService::class.java)
        enhancer.setCallback(TimeMethodInterceptor(target))
        val proxy = enhancer.create() as ConcreteService
        log.info("targetClass=${target::class.java}")
        log.info("proxyClass=${proxy::class.java}")
        proxy.call()
    }
}
