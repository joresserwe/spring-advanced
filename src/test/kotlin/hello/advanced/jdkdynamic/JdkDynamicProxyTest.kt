package hello.advanced.jdkdynamic

import hello.advanced.jdkdynamic.code.*
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy

internal class JdkDynamicProxyTest {

    private val log = KotlinLogging.logger {}

    @Test
    fun dynamicA() {
        val target = AInterfaceImpl()

        val proxy = Proxy.newProxyInstance(
            AInterface::class.java.classLoader,
            arrayOf(AInterface::class.java),
            TimeInvocationHandler(target)
        ) as AInterface
        proxy.call()
        log.info("targetClass=${target::class}")
        log.info("proxyClass=${proxy::class}")
    }

    @Test
    fun dynamicB() {
        val target = BInterfaceImpl()

        val proxy = Proxy.newProxyInstance(
            BInterface::class.java.classLoader,
            arrayOf(BInterface::class.java),
            TimeInvocationHandler(target)
        ) as BInterface
        proxy.call()
        log.info("targetClass=${target::class}")
        log.info("proxyClass=${proxy::class}")
    }
}
