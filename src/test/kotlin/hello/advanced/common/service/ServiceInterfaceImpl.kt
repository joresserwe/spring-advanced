package hello.advanced.common.service

import mu.KotlinLogging

class ServiceInterfaceImpl : ServiceInterface {
    private val log = KotlinLogging.logger {}
    override fun save() {
        log.info("save 호출")
    }

    override fun find() {
        log.info("find 호출")
    }
}
