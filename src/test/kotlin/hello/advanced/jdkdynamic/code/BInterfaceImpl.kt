package hello.advanced.jdkdynamic.code

import mu.KotlinLogging

class BInterfaceImpl : BInterface {

    private val log = KotlinLogging.logger {}
    
    override fun call(): String {
        log.info { "B 호출" }
        return "b"
    }
}
