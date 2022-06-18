package hello.advanced.pureproxy.proxy.code

import mu.KotlinLogging

class CacheProxy(
    private val target: Subject
) : Subject {
    private val log = KotlinLogging.logger {}

    private lateinit var cacheValue: String

    override fun operation(): String {
        log.info { "Proxy 호출" }
        if (!::cacheValue.isInitialized) {
            cacheValue = target.operation()
        }
        return cacheValue
    }

}
