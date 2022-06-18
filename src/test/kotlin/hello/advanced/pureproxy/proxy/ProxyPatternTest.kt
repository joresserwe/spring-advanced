package hello.advanced.pureproxy.proxy

import hello.advanced.pureproxy.proxy.code.CacheProxy
import hello.advanced.pureproxy.proxy.code.ProxyPatternClient
import hello.advanced.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

internal class ProxyPatternTest {

    @Test
    fun noProxyTest() {
        val realSubject = RealSubject()
        val client = ProxyPatternClient(realSubject)
        client.execute()
        client.execute()
        client.execute()
        client.execute()
    }

    @Test
    fun cacheProxyTest() {
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatternClient(cacheProxy)
        client.execute()
        client.execute()
        client.execute()
        client.execute()
    }
}
