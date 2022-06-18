package hello.advanced.common.service

import mu.KotlinLogging

open class ConcreteService {
    private val log = KotlinLogging.logger {}

    open fun call() {
        log.info("ConcreateService 호출")
    }
}
