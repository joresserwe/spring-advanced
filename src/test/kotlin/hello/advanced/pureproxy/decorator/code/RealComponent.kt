package hello.advanced.pureproxy.decorator.code

import mu.KotlinLogging

open class RealComponent : Component {
    private val log = KotlinLogging.logger {}
    override fun operation(): String {
        log.info { "RealComponent 실행" }
        return "data"
    }
}
