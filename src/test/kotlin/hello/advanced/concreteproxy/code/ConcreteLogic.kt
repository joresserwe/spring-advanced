package hello.advanced.concreteproxy.code

import mu.KotlinLogging

open class ConcreteLogic {

    private val log = KotlinLogging.logger {}

    open fun operation(): String {
        log.info { "Concrete Logic 실행" }
        return "data"
    }
}
