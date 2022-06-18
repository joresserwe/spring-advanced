package hello.advanced.jdkdynamic.code

import mu.KotlinLogging

class AInterfaceImpl : AInterface {
    private val log = KotlinLogging.logger {}
    override fun call(): String {
        log.info { "A 호출" }
        return "a"
    }
}
