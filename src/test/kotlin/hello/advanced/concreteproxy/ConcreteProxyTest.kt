package hello.advanced.concreteproxy

import hello.advanced.concreteproxy.code.ConcreteClient
import hello.advanced.concreteproxy.code.ConcreteLogic
import hello.advanced.concreteproxy.code.TimeProxy
import org.junit.jupiter.api.Test

internal class ConcreteProxyTest {

    @Test
    fun noProxy() {
        val concreteLogic = ConcreteLogic()
        val concreteClient = ConcreteClient(ConcreteLogic())
        concreteClient.execute()
    }

    @Test
    fun addProxy() {
        val timeProxy = TimeProxy()
        val concreteClient = ConcreteClient(timeProxy)
        concreteClient.execute()
    }
}
