package hello.advanced.pureproxy.proxy.code

import mu.KotlinLogging

class RealSubject : Subject {

    private val log = KotlinLogging.logger {}

    override fun operation(): String {
        log.info { "실제 객체 호출" }
        Thread.sleep(1000)
        return "data"
    }
}

